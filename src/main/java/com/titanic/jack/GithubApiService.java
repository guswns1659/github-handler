package com.titanic.jack;

import com.titanic.jack.utils.LocalDateTimeComparison;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GithubApiService {
    private GithubClientApator githubClientApator;

    public GithubApiService(GithubClientApator githubClientApator) {
        this.githubClientApator = githubClientApator;
    }

    public List<AlonePr> getAlonePrs() {
        githubClientApator.getPullRequestInfo()
                .filter(this::isMoreThanThreeWeekDays)
                .filter(pr -> isMoreThanFiveWeekDays(pr) || isNoneComments(pr));

        /**
         * 외로운 pr 조건
         * - Approved X
         * - opened
         * - Draft X
         * - base label X
         * - created < now() - working day 3. (생성된지 워킹데이 기준 3일 이상인 경우)
         * - 남이 남긴 comments > 0 && now() - createdAt > working day 5 (코멘트가 있어도 워킹데이 기준 5일 이상인 경우)
         * - 남이 남긴 comments < 1
         */
        return null;
    }

    public boolean isMoreThanThreeWeekDays(PrWithCommentsAndReviews pr) {
        return LocalDateTimeComparison.isMoreThanThreeWeekdaysApart(
                pr.githubPullRequest().createdAt(),
                LocalDateTime.now()
        );
    }

    public boolean isMoreThanFiveWeekDays(PrWithCommentsAndReviews pr) {
        return !pr.hasOnlyOwnerComments()
                &&
                LocalDateTimeComparison.isMoreThanThreeWeekdaysApart(pr.githubPullRequest().createdAt(), LocalDateTime.now());
    }

    public boolean isNoneComments(PrWithCommentsAndReviews pr) {
        return pr.hasOnlyOwnerComments();
    }
}

record AlonePr() {}
