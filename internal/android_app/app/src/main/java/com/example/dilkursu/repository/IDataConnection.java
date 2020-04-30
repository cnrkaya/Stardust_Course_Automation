package com.example.dilkursu.repository;

import com.example.dilkursu.models.Credential;

public interface IDataConnection {

    public Credential checkUserCredentials(String email, String password);

}
