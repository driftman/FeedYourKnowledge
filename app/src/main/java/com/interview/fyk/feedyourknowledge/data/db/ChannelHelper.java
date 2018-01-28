package com.interview.fyk.feedyourknowledge.data.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import java.io.StringWriter;
import com.interview.fyk.feedyourknowledge.data.model.Channel;

/**
 * Created by abk on 28/01/2018.
 */

public class ChannelHelper {

    // statics
    public static final String TAG = ChannelHelper.class.getSimpleName();

    private static final String SEPARATOR = "_";
    private static final String CHANNEL = "CHANNEL";
    private static final String CHANNELS = "CHANNELS";
    private static ChannelHelper channelHelper;

    // variables
    private Context context;
    private Serializer serializer;
    private SharedPreferences sharedPreferences;

    public static ChannelHelper getInstance(Context context) {
        if(channelHelper == null)
            channelHelper = new ChannelHelper(context);
        return channelHelper;
    }

    public void set(String key, Channel channel) {
        String xml;
        try {
             xml = serializeChannel(channel);
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
            xml = null;
        }
        if(xml != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(CHANNEL + SEPARATOR + key, xml);
            editor.commit();
        }
    }

    public Channel get(String key) {
        String xml = sharedPreferences.getString(CHANNEL + SEPARATOR + key, "");
        if(xml.equals(""))
            return null;
        Channel channel;
        try {
            channel = deserializeChannel(xml);
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
            channel = null;
        }
        return channel;
    }


    private ChannelHelper(Context context) {
        this.context = context;
        serializer = new Persister();
        sharedPreferences = context.getSharedPreferences(CHANNELS, Context.MODE_PRIVATE);
    }

    private String serializeChannel(Channel channel) throws Exception {
        StringWriter writer = new StringWriter();
        serializer.write(channel, writer);
        String xml = writer.toString();
        return xml;
    }

    private Channel deserializeChannel(String xml) throws Exception {
        Boolean valid = serializer.validate(Channel.class, xml);
        if(valid)
            return serializer.read(Channel.class, xml);
        else
            return null;
    }
}
