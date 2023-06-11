package com.webtracker.app.repository;

import com.webtracker.app.model.github.GitHubOwner;

import java.util.ArrayList;
import java.util.List;

public class GitHubOwnerRepository {

    private static final List<GitHubOwner> repository = new ArrayList<>();

    static {
        repository.addAll(List.of(
                new GitHubOwner(1, "Alan", "Smith", "alan998", "somemail@gmail.com"),
                new GitHubOwner(2, "Ron", "Marvin", "rm112", "1234@gmail.com")));
    }

    public static List<GitHubOwner> getAll() {
        return new ArrayList<>(repository);
    }

    public static GitHubOwner getByUsername(String username) {
        return new GitHubOwner(1, "John", "Smith", "Next3K", null);
    }

    public static void addALl(List<GitHubOwner> observerList) {
        repository.addAll(observerList);
    }
}

