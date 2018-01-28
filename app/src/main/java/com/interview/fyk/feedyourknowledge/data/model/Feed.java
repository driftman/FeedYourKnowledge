package com.interview.fyk.feedyourknowledge.data.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by abk on 26/01/2018.
 */

@Root(name = "rss", strict = false)
public class Feed {
    @Element(name = "channel")
    private Channel mChannel;

    public Feed() {
    }

    public Feed(Channel mChannel) {
        this.mChannel = mChannel;
    }

    public Channel getmChannel() {
        return mChannel;
    }


}
