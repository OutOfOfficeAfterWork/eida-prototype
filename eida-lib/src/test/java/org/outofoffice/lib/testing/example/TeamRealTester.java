package org.outofoffice.lib.testing.example;

import org.outofoffice.lib.context.EidaContext;
import org.outofoffice.lib.core.socket.EidaDefaultSocketClient;
import org.outofoffice.lib.core.socket.EidaSocketClientLoggingProxy;
import org.outofoffice.lib.example.Team;
import org.outofoffice.lib.example.TeamRepository;

import static org.assertj.core.api.Assertions.assertThat;

public class TeamRealTester {

    public static void main(String[] args) {
        EidaSocketClientLoggingProxy socketClient = new EidaSocketClientLoggingProxy(new EidaDefaultSocketClient());
        EidaContext.init(TeamRealTester.class, socketClient);
        TeamRepository repository = (TeamRepository) EidaContext.getRepository(Team.class);

        Team team = repository.find(1L).orElseThrow();

        Team outOfOffice = Team.builder().id(1L).teamName("OutOfOffice").build();
        assertThat(team).isEqualTo(outOfOffice);
    }

}
