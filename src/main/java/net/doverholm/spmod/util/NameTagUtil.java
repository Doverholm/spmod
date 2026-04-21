package net.doverholm.spmod.util;

import net.doverholm.spmod.rank.Rank;
import net.doverholm.spmod.rank.RankManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class NameTagUtil {

    public static void updateName(ServerPlayerEntity player) {

        Scoreboard scoreboard = player.getServer().getScoreboard();

        Rank rank = RankManager.getRank(player.getUuid());

        String teamName = rank.name().toLowerCase();

        Team team = scoreboard.getTeam(teamName);

        if (team == null) {
            team = scoreboard.addTeam(teamName);
        }

        team.setPrefix(getPrefix(rank));

        scoreboard.addScoreHolderToTeam(player.getName().getString(), team);
    }

    private static Text getPrefix(Rank rank) {
        return switch (rank) {
            case ADMIN -> Text.literal("[ADMIN] ").formatted(Formatting.RED);
            case MODERATOR -> Text.literal("[MOD] ").formatted(Formatting.BLUE);
            default -> Text.literal("");
        };
    }
}