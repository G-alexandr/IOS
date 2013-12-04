package org.springframework.samples.petclinic.web;

import com.wolfram.alpha.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Task;
import org.springframework.samples.petclinic.model.TaskTask;
import org.springframework.samples.petclinic.repository.springdatajpa.TasksRepository;
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


    @Autowired
    private TasksRepository tasksRepository;

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
        mav.addObject(FORM, new TaskTask());

        return mav;
    }

    @RequestMapping(value = "task/makeResolve", method = RequestMethod.POST)
    public ModelAndView makeResolve(@ModelAttribute(FORM) TaskTask taskAnswer) {

        String input = (taskAnswer.getFormulas()+taskAnswer.getVariables()).toLowerCase();
        String answer = "nothing";

        WAEngine engine = new WAEngine();
        engine.setAppID(appid);
        engine.addFormat("plaintext");
        WAQuery query = engine.createQuery();
        query.setInput(input);
        try {

            WAQueryResult queryResult = engine.performQuery(query);

            if (queryResult.isError()) {
                System.out.println("Query error");
                System.out.println("  error code: " + queryResult.getErrorCode());
                System.out.println("  error message: " + queryResult.getErrorMessage());

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
        return mav;

    }
}
