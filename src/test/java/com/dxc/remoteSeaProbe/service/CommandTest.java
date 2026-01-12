package com.dxc.remoteSeaProbe.service;

import com.dxc.remoteSeaProbe.dto.Command;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandTest {

    @ParameterizedTest(name = "Invalid command: \"{0}\"")
    @ValueSource(strings = {
            "",
            " ",
            "jump",
            "north",
            "forwardd",
            "123",
            "LEFT_RIGHT"
    })
    void shouldRejectInvalidCommands(String input) {

        assertThrows(IllegalArgumentException.class,
                () -> Command.from(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "forward",
            "FORWARD",
            " Forward ",
            "left",
            "RIGHT",
            "stay"
    })
    void shouldParseValidCommands(String input) {

        Command command = Command.from(input);

        assertNotNull(command);
    }

}

