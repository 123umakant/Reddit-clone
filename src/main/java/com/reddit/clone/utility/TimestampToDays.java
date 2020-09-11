package com.reddit.clone.utility;

public class TimestampToDays {

    public  String getDays(long timestamp){

        timestamp = (timestamp/1000);

        double currenttime = (System.currentTimeMillis()/1000);

        double enqtime   = currenttime-timestamp;

        long posttime = 0;

        String time_desc="";

        if (enqtime <3600) {

            posttime = Math.round((enqtime/60));
                if(posttime==0){
                    time_desc= "just now";
                }
                else {
                    time_desc = posttime + " minute ago";
                }
        }

        else if ((enqtime>=3600) && (enqtime<86400)) {

            posttime = Math.round((enqtime/3600));

            time_desc=posttime+" hour ago";

        }

        else if ((enqtime>=86400) && (enqtime<604800)) {


            posttime = Math.round((enqtime/86400));

            time_desc=posttime+" day ago";

        }


        else if ((enqtime>=604800) && (enqtime<2592000)) {

            posttime = Math.round((enqtime/604800));

            time_desc=posttime+" week ago";

        }

        else if ((enqtime>=2592000) && (enqtime<31104000)) {


            posttime = Math.round((enqtime/2592000));

            time_desc=posttime+" month ago";

        }

        else if ((enqtime>31104000)) {


            posttime = Math.round((enqtime/31104000));

            time_desc=posttime+" year ago";

        }

        return time_desc;

    }
}
