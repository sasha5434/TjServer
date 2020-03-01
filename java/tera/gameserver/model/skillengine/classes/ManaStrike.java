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
package tera.gameserver.model.skillengine.classes;

import tera.gameserver.manager.ObjectEventManager;
import tera.gameserver.model.AttackInfo;
import tera.gameserver.model.Character;
import tera.gameserver.model.skillengine.StatType;
import tera.gameserver.templates.SkillTemplate;

import rlib.util.VarTable;

/**
 * @author Ronn
 */
public class ManaStrike extends Strike
{
	
	private final int gainMp;
	
	/**
	 * Constructor for ManaStrike.
	 * @param template SkillTemplate
	 */
	public ManaStrike(SkillTemplate template)
	{
		super(template);
		final VarTable vars = template.getVars();
		gainMp = vars.getInteger("gainMp", 0);
	}
	
	/**
	 * Method applySkill.
	 * @param attacker Character
	 * @param target Character
	 * @return AttackInfo
	 * @see tera.gameserver.model.skillengine.Skill#applySkill(Character, Character)
	 */
	@Override
	public AttackInfo applySkill(Character attacker, Character target)
	{
		final AttackInfo info = super.applySkill(attacker, target);
		int abs = (int) attacker.calcStat(StatType.ABSORPTION_MP_ON_MAX, 0, attacker, null);
		abs = gainMp + ((gainMp * abs) / 100);
		
		if (abs > 0)
		{
			attacker.setCurrentMp(attacker.getCurrentMp() + abs);
			final ObjectEventManager eventManager = ObjectEventManager.getInstance();
			eventManager.notifyMpChanged(attacker);
		}
		
		return info;
	}
}