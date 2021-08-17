package mentortools.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import javax.annotation.Nullable;
import java.net.URI;

public class MentorEntityNotFoundException extends AbstractThrowableProblem {
    public MentorEntityNotFoundException(String uri, String title, String detail) {
        super(
                URI.create(uri),
                title,
                Status.NOT_FOUND,
                detail);
    }
}
