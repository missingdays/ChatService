package reply.error;

import utils.HttpCode;

public class MethodNotAllowedReply extends BadRequestReply {
    @Override
    public int getStatusCode() {
        return HttpCode.METHOD_NOT_ALLOWED.getValue();
    }
}
