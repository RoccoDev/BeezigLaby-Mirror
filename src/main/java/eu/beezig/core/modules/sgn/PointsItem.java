/*
 * Copyright (C) 2019 Beezig (RoccoDev, ItsNiklass)
 *
 * This file is part of Beezig.
 *
 * Beezig is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Beezig is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Beezig.  If not, see <http://www.gnu.org/licenses/>.
 */

package eu.beezig.core.modules.sgn;

import eu.beezig.core.Log;
import eu.beezig.core.games.SGN;
import eu.beezig.core.hiveapi.APIValues;
import eu.the5zig.mod.modules.GameModeItem;
import eu.the5zig.util.minecraft.ChatColor;

public class PointsItem extends GameModeItem<SGN> {

    public PointsItem() {
        super(SGN.class);
    }

    private String getMainFormatting() {
        return "§r";
    }

    @Override
    protected Object getValue(boolean dummy) {
        try {
            if ((boolean) getProperties().getSetting("showrank").get()) {
                StringBuilder sb = new StringBuilder();
                if ((boolean) getProperties().getSetting("showcolor").get()) {
                    sb.append(Log.df(APIValues.SGNpoints)).append(" (").append(SGN.rank).append(getMainFormatting());

                } else {

                    sb.append(Log.df(APIValues.SGNpoints)).append(" (").append(ChatColor.stripColor(SGN.rank));
                }

                if ((boolean) getProperties().getSetting("showpointstonextrank").get()) {
                    if (SGN.rankObject == null) return Log.df(APIValues.SGNpoints);
                    sb.append((boolean) getProperties().getSetting("showcolor").get() ? " / " + SGN.rankObject.getPointsToNextRank((int) APIValues.SGNpoints) : " / " + ChatColor.stripColor(SGN.rankObject.getPointsToNextRank((int) APIValues.SGNpoints)));

                }
                sb.append(

                        (boolean) getProperties().getSetting("showcolor").get() ?

                                getMainFormatting() + ")" :
                                ")");
                return sb.toString().trim();
            }
            return Log.df(APIValues.SGNpoints);
        } catch (Exception e) {
            e.printStackTrace();
            return "Server error";
        }
    }

    @Override
    public String getTranslation() {
        return "beezig.module.points";
    }

    @Override
    public void registerSettings() {
        getProperties().addSetting("showrank", false);
        getProperties().addSetting("showcolor", true);
        getProperties().addSetting("showpointstonextrank", false);
    }

    @Override
    public boolean shouldRender(boolean dummy) {
        try {
            if (getGameMode() == null) return false;
            return dummy || (SGN.shouldRender(getGameMode().getState()));
        } catch (Exception e) {
            return false;
        }
    }

}
