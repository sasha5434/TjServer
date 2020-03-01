/*
 * This file is part of TJServer.
 * 
 * TJServer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * TJServer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package tera.gameserver.model.npc;

import tera.gameserver.templates.NpcTemplate;

/**
 * @author Ronn
 */
public class RaidBoss extends Monster
{
	/**
	 * Constructor for RaidBoss.
	 * @param objectId int
	 * @param template NpcTemplate
	 */
	public RaidBoss(int objectId, NpcTemplate template)
	{
		super(objectId, template);
	}
	
	/**
	 * Method isOwerturnImmunity.
	 * @return boolean
	 */
	@Override
	public boolean isOwerturnImmunity()
	{
		return true;
	}
	
	/**
	 * Method isLeashImmunity.
	 * @return boolean
	 */
	@Override
	public boolean isLeashImmunity()
	{
		return true;
	}
	
	/**
	 * Method isRaidBoss.
	 * @return boolean
	 */
	@Override
	public boolean isRaidBoss()
	{
		return true;
	}
}