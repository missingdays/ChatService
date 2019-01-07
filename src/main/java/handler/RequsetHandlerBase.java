package handler;

import Entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import reply.Reply;
import reply.error.BadRequestReply;
import reply.error.UnAuthorizedReply;
import request.Request;
import utils.db.MessageDAO;
import utils.db.MessageDAOImplDummy;
import utils.db.UserDAO;
import utils.db.UserDAOImplDummy;

public abstract class RequsetHandlerBase implements RequestHandler {
    @Override
    final public @NotNull Reply handle(@NotNull Request request) {
        logger.debug("processing request for request: {}", request);

        if (!checkAuthorization(request)) {
            logger.warn("bad authorization in request: {}", request);
            return invalidAuthorizationReply(request);
        }

        if (!isRequestValid(request)) {
            logger.warn("invalid request: {}", request);
            return new BadRequestReply();
        }

        return createReply(request);
    }

    /**
     * @return true if authorization was success
     */
    private boolean checkAuthorization(Request request) {
        user = userDAO.getUserByToken(request.getAuthorizationToken());
        return user != null;
    }

    protected abstract boolean isAllowedMethod(Request request);

    /**
     * @return true if request body is valid
     * @see should NOT check authorization token!!!!
     */
    protected abstract boolean isRequestValid(Request request);

    protected abstract @NotNull Reply createReply(Request request);

    @NotNull
    protected Reply invalidAuthorizationReply(Request request) {
        logger.warn("bad authorization with request: {}", request);
        return new UnAuthorizedReply();
    }

    protected User user;
    protected UserDAO userDAO = new UserDAOImplDummy();
    protected MessageDAO messageDAO = new MessageDAOImplDummy();
    protected Logger logger = LogManager.getLogger(this.getClass());
}
