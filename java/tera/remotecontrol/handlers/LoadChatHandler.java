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
package tera.remotecontrol.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

import tera.remotecontrol.Packet;
import tera.remotecontrol.PacketHandler;
import tera.remotecontrol.PacketType;

import rlib.concurrent.Locks;

/**
 * @author Ronn
 * @created 09.04.2012
 */
public class LoadChatHandler implements PacketHandler
{
	public static final LoadChatHandler instance = new LoadChatHandler();
	
	private static final List<String> messages = new ArrayList<>();
	
	private static final Lock lock = Locks.newLock();
	
	/**
	 * Method add.
	 * @param message String
	 */
	public static void add(String message)
	{
		lock.lock();
		
		try
		{
			if (messages.size() > 200)
			{
				messages.clear();
			}
			
			messages.add(message);
		}
		
		finally
		{
			lock.unlock();
		}
	}
	
	/**
	 * Method processing.
	 * @param packet Packet
	 * @return Packet
	 * @see tera.remotecontrol.PacketHandler#processing(Packet)
	 */
	@Override
	public Packet processing(Packet packet)
	{
		lock.lock();
		
		try
		{
			final ArrayList<String> buffer = new ArrayList<>(messages);
			messages.clear();
			return new Packet(PacketType.RESPONSE, buffer);
		}
		
		finally
		{
			lock.unlock();
		}
	}
}
