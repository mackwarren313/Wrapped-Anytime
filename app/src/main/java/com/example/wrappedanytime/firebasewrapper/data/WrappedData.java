package com.example.wrappedanytime.firebasewrapper.data;

import java.time.DateTimeException;
import java.util.Date;
import java.util.UUID;

public class WrappedData {
    private String userId;
    private UUID wrappedId;
    private Date dataTimeCreated; // enforce time stamp?
}
