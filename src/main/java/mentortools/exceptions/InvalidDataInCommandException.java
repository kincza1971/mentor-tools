package mentortools.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;
import java.net.URI;

public class InvalidDataInCommandException extends AbstractThrowableProblem {
    public InvalidDataInCommandException(String uri, String title, String detail) {
        super(
                URI.create(uri),
                title,
                Status.INTERNAL_SERVER_ERROR,
                detail);
    }
}
