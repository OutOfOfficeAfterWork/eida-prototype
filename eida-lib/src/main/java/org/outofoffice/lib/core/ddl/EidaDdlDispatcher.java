package org.outofoffice.lib.core.ddl;

import lombok.RequiredArgsConstructor;
import org.outofoffice.lib.core.client.EidaDdlManagerClient;
import org.outofoffice.lib.core.client.EidaDdlShardClient;
import org.outofoffice.lib.core.client.EidaDllClient;
import org.outofoffice.lib.core.ddl.param.CreateTableParam;
import org.outofoffice.lib.core.ddl.param.DeleteTableParam;
import org.outofoffice.lib.core.ddl.param.RenameTableParam;


@RequiredArgsConstructor
public class EidaDdlDispatcher {
    private final EidaDllClient eidaDllClient;
    private final EidaDdlManagerClient eidaDdlManagerClient;
    private final EidaDdlShardClient eidaDdlShardClient;

    public void create(CreateTableParam param) {

    }

    public void rename(RenameTableParam toParam) {

    }

    public void delete(DeleteTableParam toParam) {

    }
}
