package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.service.bf4db;

import br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.dto.Bf4dbPlayerResponse;

public interface RequestApiBf4dbService {

    Bf4dbPlayerResponse getPlayerData(String userCode, String apiToken);
}
