package com.reddit.clone.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@RestController
@RequestMapping("/twitter")
public class TwitterController {

/*    @Autowired
    private Twitter twitter;*/
/*

    @RequestMapping(value = "/tweet")
        public List<Tweet> getTweets(@PathVariable final String hashTag) {
            System.out.println("INSIDE TWITTER");
            return twitter.searchOperations().search(hashTag, 20).getTweets();
        }
*/

    @RequestMapping(value = "/tweet")
    public String getTweets() throws TwitterException {

        Twitter twitter = TwitterFactory.getSingleton();
        String message = "\"this is working \" by Euromaxx: Lifestyle Europe (DW) \n http://bit.ly/1cHB7MH";
        System.out.println(twitter.updateStatus(message).getText());
        return "";
    }

}
