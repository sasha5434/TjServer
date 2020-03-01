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
package tera.gameserver.model.skillengine.shots;

import tera.gameserver.IdFactory;
import tera.gameserver.model.Character;
import tera.gameserver.model.skillengine.Skill;
import tera.gameserver.network.serverpackets.CharObjectDelete;
import tera.gameserver.network.serverpackets.StartObjectShot;

import rlib.util.pools.FoldablePool;
import rlib.util.pools.Pools;

/**
 * @author Ronn
 */
public class ObjectShot extends AbstractShot
{
	
	private static FoldablePool<ObjectShot> pool = Pools.newConcurrentFoldablePool(ObjectShot.class);
	
	/**
	 * Method startShot.
	 * @param caster Character
	 * @param skill Skill
	 * @param targetX float
	 * @param targetY float
	 * @param targetZ float
	 */
	public static void startShot(Character caster, Skill skill, float targetX, float targetY, float targetZ)
	{
		ObjectShot shot = pool.take();
		
		if (shot == null)
		{
			shot = new ObjectShot();
		}
		
		shot.prepare(caster, skill, targetX, targetY, targetZ);
		shot.start();
	}
	
	protected int objectId;
	
	public ObjectShot()
	{
		setType(ShotType.SLOW_SHOT);
	}
	
	/**
	 * Method getObjectId.
	 * @return int
	 * @see tera.gameserver.model.skillengine.shots.Shot#getObjectId()
	 */
	@Override
	public int getObjectId()
	{
		return objectId;
	}
	
	/**
	 * Method start.
	 * @see tera.gameserver.model.skillengine.shots.Shot#start()
	 */
	@Override
	public synchronized void start()
	{
		super.start();
		final IdFactory idFactory = IdFactory.getInstance();
		setObjectId(idFactory.getNextShotId());
		caster.broadcastPacket(StartObjectShot.getInstance(caster, skill, this));
	}
	
	/**
	 * Method stop.
	 * @see tera.gameserver.model.skillengine.shots.Shot#stop()
	 */
	@Override
	public synchronized void stop()
	{
		super.stop();
		caster.broadcastPacket(CharObjectDelete.getInstance(objectId, getSubId()));
		pool.put(this);
	}
	
	/**
	 * Method setObjectId.
	 * @param objectId int
	 */
	public void setObjectId(int objectId)
	{
		this.objectId = objectId;
	}
}
