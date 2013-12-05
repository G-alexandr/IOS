package org.springframework.samples.petclinic.web;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.samples.petclinic.repository.springdatajpa.ThemeContentRepository;
import org.springframework.samples.petclinic.service.ContentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Dmitry Witkowsky
 * Date: 04.12.13
 * Time: 2:10
 */
@Controller
@RequestMapping("main/themes/content")
public class ContentController extends AbstractBaseController {

    @Autowired
    private ThemeContentRepository themeContentRepository;

    @Autowired
    private ContentService contentService;

    @Autowired
    private MainController mainController;

    @RequestMapping("/{themeId}/finish/{contentId}")
    public String finish(@PathVariable Integer themeId,@PathVariable Integer contentId, Map<String,Object> model){
        contentService.handleFinishReading(contentId);
        return mainController.getThemeContents(model, themeId);
    }

    @RequestMapping("/{id}/play.mp3")
    @ResponseBody
    public FileSystemResource getWav(@PathVariable Integer id){
        URL url;
        try {
            String text = themeContentRepository.findOne(id).getContentBody();
            text = stripTags(text);
            int lenght = text.length();
            final File tempFile = File.createTempFile(PREFIX, SUFFIX);
            try (FileOutputStream out = new FileOutputStream(tempFile)) {
                BufferedOutputStream outputStream = new BufferedOutputStream(out);
                for(int i = 0; i < lenght;){
                    int end = i + 100;
                    if(end >= lenght){
                        end = lenght - 1;
                    }
                    url = new URL("http://translate.google.com/translate_tts?ie=UTF-8&tl=ru&q=" + URLEncoder.encode(text.substring(i,end) , "UTF-8"));
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.addRequestProperty("User-Agent", USER_AGENT);
                    conn.connect();


                    InputStream audioSrc = conn.getInputStream();
                    DataInputStream read = new DataInputStream(audioSrc);

                    IOUtils.copy(read, outputStream);

                    i=end + 1;
                }
            }
            return new FileSystemResource(tempFile);
        } catch (IOException  e) {
            e.printStackTrace();
        }
        return null;
    }

    public String stripTags(String xmlStr){
        xmlStr = xmlStr.replaceAll("<(.)+?>", "");

        xmlStr = xmlStr.replaceAll("<(\n)+?>", "");

        return xmlStr;
    }

    private static final String PREFIX = "stream2file";
    private static final String SUFFIX = ".tmp";

}
