package org.springframework.samples.petclinic.web;

import com.wolfram.alpha.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Task;
import org.springframework.samples.petclinic.model.TaskTask;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.springdatajpa.TasksRepository;
import org.springframework.samples.petclinic.repository.springdatajpa.UserRepository;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @autor a.garelik
 * Date: 03/12/13
 * Time: 23:29
 */
@Controller
@SessionAttributes("visit")
public class TasksController extends AbstractBaseController {


    private static final String SOLUTION = "solution";
    private static final String POINTS = "points";
    private static final String TRUTH = "Truth! Great solution ";
    private static final String FALSE = "False! Your solution is wrong.";
    @Autowired
    private TasksRepository tasksRepository;
    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value = "*/tasks/{taskId}", method = RequestMethod.GET)
    public ModelAndView processTask(@PathVariable int taskId) {
        ModelAndView mav;
        Task task = tasksRepository.findOne(taskId);

        if(task.getType().equals(TASKTYPE_TASK))  {
            mav = new ModelAndView("tasks/processTask");
            mav.addObject(TASKMAIN, task);
            mav.addObject(TASKCONTENT, task.getTaskContents().get(0));
            mav.addObject(CONTENTPART, -1);
        }
        else {
            mav = new ModelAndView("tasks/processTest");
            mav.addObject(TASKMAIN, task);
            mav.addObject(TASKCONTENT, task.getTaskContents().get(0));
            mav.addObject(CONTENTPART, 0);
        }
        mav.addObject(FORM, new TaskTask(taskId));

        return mav;
    }

    @RequestMapping(value = "task/makeResolve", method = RequestMethod.POST)
    public ModelAndView makeResolve(@ModelAttribute(FORM) TaskTask taskAnswer) {
        Task task = tasksRepository.findOne(taskAnswer.getTaskId());
        String input = (taskAnswer.getFormulas()+taskAnswer.getVariables()).toLowerCase();
        StringBuffer buf = new StringBuffer();
        for (String st : input.split(",")){
            if(!st.isEmpty()){
                buf.append(st).append(",");
            }
        }
        input = buf.toString();
        String answer = "wrong";
        String solution = "solution";

        WAEngine engine = new WAEngine();
        engine.setAppID(appid);
        engine.addFormat("plaintext");
        WAQuery query = engine.createQuery();
        query.setInput(input);
        try {

            WAQueryResult queryResult = engine.performQuery(query);

            if (queryResult.isError()) {
//                System.out.println("Query error");
//                System.out.println("  error code: " + queryResult.getErrorCode());
//                System.out.println("  error message: " + queryResult.getErrorMessage());

                return new ModelAndView("/error?error=error message:" + queryResult.getErrorMessage());

            } else if (!queryResult.isSuccess()) {
//                System.out.println("Query was not understood; no results available.");
                  answer = "SOMETHING WRONG!!!<br>Query was not understood; no results available.";

            } else {
                // Got a result.
                for (WAPod pod : queryResult.getPods()) {
                    if (!pod.isError()) {
                        if(pod.getTitle().equals("Solution")){

                            for (WASubpod subpod : pod.getSubpods()) {
                                for (Object element : subpod.getContents()) {
                                    if (element instanceof WAPlainText) {
                                        answer=((WAPlainText) element).getText();
                                        solution = rateSolution(task.getTaskContents().get(0).getAnswer(),
                                                answer, taskAnswer.getAnswer(), task.getTaskContents().get(0).getScore());
                                    }
                                }
                            }
                        }
                    }
                }

            }
        } catch (WAException e) {
            e.printStackTrace();
        }

        //make result
        ModelAndView mav = new ModelAndView("tasks/result");
        mav.addObject(ANSWER, answer);
        mav.addObject(SOLUTION, solution);
        if(solution.equals(TRUTH))
            mav.addObject(POINTS, task.getTaskContents().get(0).getScore());
        else
            mav.addObject(POINTS, 0);
        return mav;

    }

    private String rateSolution(int correctAnswer, String wolframAnswer, String ansverValue, int score){


        if(wolframAnswer.contains(String.valueOf(correctAnswer))){
            User user = userRepository.findOne(UserService.getCurrentUser().getId());
            user.setScore(user.getScore()+score);
            userRepository.saveAndFlush(user);
            return TRUTH;
        }

                //поверь, мне очень стыдно
        return FALSE;

    }
}
