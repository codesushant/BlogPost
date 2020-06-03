package com.learn.raj;

import com.learn.raj.requests.BlogRequest;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.util.StringUtil;

@Slf4j
public class ValidationUtils {
    private ValidationUtils(){
    }
    public static void validateBlog(BlogRequest blogRequest){
        boolean validRequest = true;

        if(blogRequest == null || StringUtil.isEmpty(blogRequest.getAuthorName()) ||
                StringUtil.isEmpty(blogRequest.getTitle() )|| StringUtil.isEmpty(blogRequest.getContent())
        || StringUtil.isEmpty(blogRequest.getUserName())){
            log.error("All fields in request must not be empty");
            validRequest = false;
        }

        if(!validRequest){
            log.error("Invalid request");
            throw BlogException.create("Fields are empty", ResponseCode.REQUEST_EMPTY);
        }

    }
}
