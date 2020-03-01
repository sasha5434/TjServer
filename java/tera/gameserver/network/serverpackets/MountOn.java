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
package tera.gameserver.network.serverpackets;

import tera.gameserver.model.playable.Player;
import tera.gameserver.network.ServerPacketType;

/**
 * @author Ronn
 */
public class MountOn extends ServerPacket
{
	private static final ServerPacket instance = new MountOn();
	
	/**
	 * Method getInstance.
	 * @param player Player
	 * @param skillId int
	 * @return MountOn
	 */
	public static MountOn getInstance(Player player, int skillId)
	{
		final MountOn packet = (MountOn) instance.newInstance();
		packet.objectId = player.getObjectId();
		packet.subId = player.getSubId();
		packet.mountId = player.getMountId();
		packet.skillId = skillId;
		return packet;
	}
	
	private int objectId;
	private int subId;
	private int mountId;
	private int skillId;
	
	/**
	 * Method getPacketType.
	 * @return ServerPacketType
	 */
	@Override
	public ServerPacketType getPacketType()
	{
		return ServerPacketType.MOUNT_ON;
	}
	
	@Override
	protected final void writeImpl()
	{
		writeOpcode();
		writeInt(objectId);
		writeInt(subId);
		writeInt(mountId);
		writeInt(skillId);
	}
}