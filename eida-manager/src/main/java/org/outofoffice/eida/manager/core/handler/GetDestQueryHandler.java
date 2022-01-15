package org.outofoffice.eida.manager.core.handler;


public class GetDestQueryHandler implements QueryHandler {
    @Override
    public String handle(String... params) {
        return "127.0.0.1:10830";
    }
}
