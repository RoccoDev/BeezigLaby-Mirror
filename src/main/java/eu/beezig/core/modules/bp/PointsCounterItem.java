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

package eu.beezig.core.modules.bp;

import eu.beezig.core.Log;
import eu.beezig.core.games.BP;
import eu.the5zig.mod.modules.GameModeItem;

public class PointsCounterItem extends GameModeItem<BP> {

    public PointsCounterItem() {
        super(BP.class);
    }

    @Override
    protected Object getValue(boolean dummy) {
        return BP.gamePts + " " + Log.t("beezig.module.points");
    }

    @Override
    public String getTranslation() {
        return "beezig.module.game";
    }

    @Override
    public boolean shouldRender(boolean dummy) {

        try {
            if (getGameMode() == null)
                return false;
            return dummy || (BP.shouldRender(getGameMode().getState()) && BP.gamePts != 0);
        } catch (Exception e) {
            return false;
        }
    }

}
