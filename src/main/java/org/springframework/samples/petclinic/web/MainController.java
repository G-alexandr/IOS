/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.web;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.samples.petclinic.model.Tasks;
import org.springframework.samples.petclinic.repository.springdatajpa.TasksRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;


@Controller
public class MainController extends AbstractBaseController{

    @Autowired
    private TasksRepository tasksRepository;

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }


    @RequestMapping("/main/tasks")
    public String showVetList(Map<String, Object> model, HttpServletRequest request) {
        if(isUserLogged())
            return getLoginPage();
        Tasks tasks = new Tasks();
        tasks.getTasksList().addAll(tasksRepository.findAll());
        model.put("tasks", tasks);

        return "/main/tasks";
    }
    @RequestMapping(value = "/main/themes", method = RequestMethod.GET)
    public String getThemesList() {

        return "main/themes";
    }
    @RequestMapping(value = "/main/statistic", method = RequestMethod.GET)
    public String getStatistic() {

        return "main/statistic";
    }

    @RequestMapping(value = "getWav.mp3")
    @ResponseBody
    public FileSystemResource getWav(){
        URL url;
        File file = null;
        try {
            String text = "Hello world";
                    URLEncoder.encode("Hello world", "UTF-8");
            url = new URL("http://translate.google.com/translate_tts?ie=UTF-8&tl=en&q="+URLEncoder.encode(text , "UTF-8"));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.addRequestProperty("User-Agent", USER_AGENT);
            conn.connect();
            InputStream audioSrc = conn.getInputStream();
            DataInputStream read = new DataInputStream(audioSrc);
            file = stream2file(read);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new FileSystemResource(file);
    }

    private static final String PREFIX = "stream2file";
    private static final String SUFFIX = ".tmp";

    private static File stream2file (InputStream in) throws IOException {
        final File tempFile = File.createTempFile(PREFIX, SUFFIX);
        tempFile.deleteOnExit();
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            IOUtils.copy(in, out);
        }
        return tempFile;
    }

}
