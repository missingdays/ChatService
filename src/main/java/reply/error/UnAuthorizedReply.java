package reply.error;

import utils.HttpCode;

public class UnAuthorizedReply extends BadRequestReply {
    @Override
    public int getStatusCode() {
        return HttpCode.UNAUTHORIZED.getValue();
    }
}
