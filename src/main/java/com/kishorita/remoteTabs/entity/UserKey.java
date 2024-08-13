package com.kishorita.remoteTabs.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

//@Document(collection = "users")
@Data
public class UserKey {

    @Indexed (unique = true)
    @NonNull
    private String userName;
    @NonNull
    private String key;
}
