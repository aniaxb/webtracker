package com.webtracker.observers;

import com.webtracker.app.model.events.Event;
import com.webtracker.app.model.observers.observer.GitHubRepoObserver;
import com.webtracker.app.model.states.github.CodingLanguage;
import com.webtracker.app.model.states.github.GitHubOwner;
import com.webtracker.app.model.states.github.GitHubRepository;
import com.webtracker.app.model.states.github.GitHubState;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.*;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GitHubRepoObserverTest {

    //Static method to provide ready arguments for testing purposes
    static Stream<Arguments> generateTestData() {

        Set<CodingLanguage> interestingLanguages = new HashSet<>();
        interestingLanguages.add(CodingLanguage.Java);

        List<CodingLanguage> languages = new ArrayList<>();
        languages.add(CodingLanguage.Java);

        GitHubRepository githubRepo1 = new GitHubRepository();
        githubRepo1.setCodingLanguages(languages);

        GitHubRepository githubRepo2 = new GitHubRepository();
        githubRepo2.setCodingLanguages(languages);

        List<GitHubRepository> repositories = new ArrayList<>();
        repositories.add(githubRepo1);
        repositories.add(githubRepo2);

        int i=0;
        for (GitHubRepository repo : repositories) {
            repo.setRepositoryID(i);
            i++;
        }

        return Stream.of(
                Arguments.of(interestingLanguages, githubRepo1, githubRepo2)
        );
}

    //Test for detecting changes between different old state and new state
    @ParameterizedTest
    @MethodSource("generateTestData")
    void observerDetectChanges(Set<CodingLanguage> interestingLanguages, GitHubRepository githubrepo1,
    GitHubRepository githubrepo2) {

        GitHubState oldState = new GitHubState();
        GitHubState newState = new GitHubState();

        GitHubRepoObserver gitHubRepoObserver = new GitHubRepoObserver(interestingLanguages);

        oldState.setGitHubAccountDescription("blah");
        oldState.setOwner(new GitHubOwner("a", "b", "exampleusername", "examplewow09876@gmail.com"));
        oldState.setObservatorEmail("examplewow09876@gmail.com");
        List<GitHubRepository> oldRepositories = new ArrayList<>();

        oldRepositories.add(githubrepo1);

        oldState.setRepositories(oldRepositories);
        gitHubRepoObserver.setOldState(oldState);

        List<GitHubRepository> newRepositories = new ArrayList<>();

        newRepositories.add(githubrepo1);
        newRepositories.add(githubrepo2);

        newState.setGitHubAccountDescription("blah");
        newState.setOwner(new GitHubOwner("a", "b", "exampleusername", "examplewow09876@gmail.com"));
        newState.setObservatorEmail("examplewow09876@gmail.com");

        newState.setRepositories(newRepositories);

        List<Event> changes =  gitHubRepoObserver.detectEvents(newState);

        assertFalse(changes.isEmpty());
    }

    //Test for detecting no changes between the same old state and new state
    @ParameterizedTest
    @MethodSource("generateTestData")
    void observerNoChangesDetected (Set<CodingLanguage> interestingLanguages, GitHubRepository githubrepo1) {

        GitHubState oldState = new GitHubState();
        GitHubState newState = new GitHubState();

        GitHubRepoObserver gitHubRepoObserver = new GitHubRepoObserver(interestingLanguages);

        oldState.setGitHubAccountDescription("blah");
        oldState.setOwner(new GitHubOwner("a", "b", "aniaxb", "examplewow09876@gmail.com"));
        oldState.setObservatorEmail("examplewow09876@gmail.com");

        List<GitHubRepository> oldRepositories = new ArrayList<>();
        oldRepositories.add(githubrepo1);

        oldState.setRepositories(oldRepositories);
        gitHubRepoObserver.setOldState(oldState);

        List<GitHubRepository> newRepositories = new ArrayList<>();
        newRepositories.add(githubrepo1);
        newState.setRepositories(newRepositories);

        newState.setGitHubAccountDescription("blah");
        newState.setOwner(new GitHubOwner("a", "b", "aniaxb", "examplewow09876@gmail.com"));
        newState.setObservatorEmail("examplewow09876@gmail.com");

        List<Event> changes =  gitHubRepoObserver.detectEvents(newState);

        assertTrue(changes.isEmpty());
    }

}