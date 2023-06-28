package com.project.model.entity;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.time.Instant;

@Setter
@Getter
@Entity("WCY20KC1S0_Michta_hash_data")
@NoArgsConstructor
public class HashData {

    @Id
    private ObjectId id;

    private String hashValue;

    private Instant createdDate;

    public HashData(String hashValue) {
        this.hashValue = hashValue;
        this.createdDate = Instant.now();
    }
}
