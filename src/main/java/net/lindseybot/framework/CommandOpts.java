package net.lindseybot.framework;

import net.notfab.lindsey.shared.rpc.FChannel;
import net.notfab.lindsey.shared.rpc.FMember;

import java.util.HashMap;

public class CommandOpts extends HashMap<String, Object> {

    public Long getLong(String name) {
        Object object = this.get(name);
        if (object == null) {
            return null;
        } else if (object instanceof String) {
            return Long.parseLong((String) object);
        } else {
            return (long) object;
        }
    }

    public FMember getMember(String name) {
        return (FMember) this.get(name);
    }

    public String getString(String name) {
        Object object = this.get(name);
        if (object == null) {
            return null;
        } else if (object instanceof String) {
            return (String) object;
        } else {
            return String.valueOf(object);
        }
    }

    public Boolean getBoolean(String name) {
        Object object = this.get(name);
        if (object == null) {
            return null;
        } else if (object instanceof String) {
            return Boolean.valueOf((String) object);
        } else {
            return (Boolean) object;
        }
    }

    public FChannel getChannel(String name) {
        return (FChannel) this.get(name);
    }

}
