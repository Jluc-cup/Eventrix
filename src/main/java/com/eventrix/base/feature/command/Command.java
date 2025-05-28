package com.eventrix.base.feature.command;

public interface Command<Context, Result> {
    Result execute(Context context);
}
