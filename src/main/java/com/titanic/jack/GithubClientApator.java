package com.titanic.jack;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class GithubClientApator {
    public Flux<PrWithCommentsAndReviews> getPullRequestInfo() {
        GithubUser githubUser1 = new GithubUser(12L);
        GithubUser githubUser2 = new GithubUser(123L);
        GithubUser githubUser3 = new GithubUser(1234L);

        return Flux.just(
                new PrWithCommentsAndReviews(
                        GithubPullRequest.of("1", 1, "nonebase", false, githubUser1),
                        List.of(new GithubPrComment(githubUser1)),
                        List.of(new GithubPrReview(githubUser1, "COMMENTED"))
                ),
                new PrWithCommentsAndReviews(
                        GithubPullRequest.of("2", 2, "base", false, githubUser2),
                        List.of(new GithubPrComment(githubUser2)),
                        List.of(new GithubPrReview(githubUser2, "APPROVED"))
                ),
                new PrWithCommentsAndReviews(
                        GithubPullRequest.of("3", 3, "nonebase", false, githubUser3),
                        List.of(new GithubPrComment(githubUser3)),
                        List.of(new GithubPrReview(githubUser3, "COMMENTED"))
                )
        );
    }
}

record PrWithCommentsAndReviews(
        GithubPullRequest githubPullRequest,
        List<GithubPrComment> githubPrComments,
        List<GithubPrReview> githubPrReviews
) {}

record GithubPrReview(
        GithubUser writer,
        String state
) {

}
record GithubPrComment(
        GithubUser writer
) {

}
record GithubPullRequest(
        String id,
        String htmlUrl,
        int number,
        String state,
        GithubUser owner,
        List<PrLabel> labels,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        boolean isDraft
) {
    public static GithubPullRequest of(String id, int number, String label, boolean isDraft, GithubUser githubUser) {
        return new GithubPullRequest(
                id,
                "htmlUrl",
                number,
                "open",
                githubUser,
                List.of(new PrLabel(label)),
                LocalDateTime.now(),
                LocalDateTime.now(),
                isDraft
        );
    }
}

record GithubUser(
        long id
) {}

record PrLabel(
        String name
) {}