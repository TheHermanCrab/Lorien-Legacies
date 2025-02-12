package lorien.legacies.legacies.implementations;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import lorien.legacies.legacies.KeyBindings;
import lorien.legacies.legacies.Legacy;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;

public class Telekinesis extends Legacy
{

	@Override
	public void computeLegacyTick(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	private static final float DISTANCE = 10;

	private HashMap<Integer, Entity> pointedEntities = new HashMap<>();
	private HashMap<Integer, Entity> previousEntities = new HashMap<>();
	private HashMap<Integer, Integer> entityIDs = new HashMap<>();
	
	// Launching variables
	private HashMap<Integer, Boolean> launchRequired = new HashMap<>();
	private HashMap<Integer, Vec3d> force = new HashMap<>();
	
	
	public Telekinesis()
	{
		super();
		LEGACY_NAME = "Telekinesis";
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	// Server side, decided what entity player is selecting
	private void updatePointedEntity(EntityPlayer entity)
	{	
		float partialTicks = Minecraft.getMinecraft().getRenderPartialTicks();
		
	    if (entity != null)
	    {
	        if (Minecraft.getMinecraft().world != null)
	        {
	            Minecraft.getMinecraft().mcProfiler.startSection("pick");
	            Minecraft.getMinecraft().pointedEntity = null;
	            //double d0 = (double)Minecraft.getMinecraft()..getBlockReachDistance();
	            double d0 = DISTANCE;
	            Minecraft.getMinecraft().objectMouseOver = entity.rayTrace(d0, partialTicks);
	            Vec3d vec3d = entity.getPositionEyes(partialTicks);
	            boolean flag = false;
	            int i = 3;
	            double d1 = d0;

	            if (Minecraft.getMinecraft().playerController.extendedReach())
	            {
	                //d1 = 6.0D;
	                //d0 = d1;
	            }
	            else
	            {
	                if (d0 > 3.0D)
	                {
	                    flag = true;
	                }
	            }

	            if (Minecraft.getMinecraft().objectMouseOver != null)
	            {
	                d1 = Minecraft.getMinecraft().objectMouseOver.hitVec.distanceTo(vec3d);
	            }

	            Vec3d vec3d1 = entity.getLook(1.0F);
	            Vec3d vec3d2 = vec3d.addVector(vec3d1.x * d0, vec3d1.y * d0, vec3d1.z * d0);
	            
	            if(pointedEntities.containsKey(entity.getEntityId()))
	            	pointedEntities.put(entity.getEntityId(), null);
	            
	            Vec3d vec3d3 = null;
	            float f = 1.0F;
	            List<Entity> list = Minecraft.getMinecraft().world.getEntitiesInAABBexcluding(entity, entity.getEntityBoundingBox().expand(vec3d1.x * d0, vec3d1.y * d0, vec3d1.z * d0).grow(1.0D, 1.0D, 1.0D), Predicates.and(EntitySelectors.NOT_SPECTATING, new Predicate<Entity>()
	            {
	                public boolean apply(@Nullable Entity p_apply_1_)
	                {
	                    return p_apply_1_ != null && p_apply_1_.canBeCollidedWith();
	                }
	            }));
	            double d2 = d1;

	            for (int j = 0; j < list.size(); ++j)
	            {
	                Entity entity1 = list.get(j);
	                AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().grow((double)entity1.getCollisionBorderSize());
	                RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(vec3d, vec3d2);

	                if (axisalignedbb.contains(vec3d))
	                {
	                    if (d2 >= 0.0D && pointedEntities.get(entity.getEntityId()) instanceof EntityPlayer == false)
	                    {
	                    	pointedEntities.put(entity.getEntityId(), entity1);
	                        vec3d3 = raytraceresult == null ? vec3d : raytraceresult.hitVec;
	                        d2 = 0.0D;
	                    }
	                }
	                else if (raytraceresult != null)
	                {
	                    double d3 = vec3d.distanceTo(raytraceresult.hitVec);

	                    if (d3 < d2 || d2 == 0.0D)
	                    {
	                        if (entity1.getLowestRidingEntity() == entity.getLowestRidingEntity() && !entity1.canRiderInteract())
	                        {
	                            if (d2 == 0.0D && pointedEntities.get(entity.getEntityId()) instanceof EntityPlayer == false)
	                            {
	                            	pointedEntities.put(entity.getEntityId(), entity1);
	                                vec3d3 = raytraceresult.hitVec;
	                            }
	                        }
	                        else if ( pointedEntities.get(entity.getEntityId()) instanceof EntityPlayer == false)
	                        {
	                        	pointedEntities.put(entity.getEntityId(), entity1);
	                            vec3d3 = raytraceresult.hitVec;
	                            d2 = d3;
	                        }
	                    }
	                }
	            }

	            if ( pointedEntities.get(entity.getEntityId()) != null && flag && vec3d.distanceTo(vec3d3) > 3.0D)
	            {
	                pointedEntities.put(entity.getEntityId(), null);
	                Minecraft.getMinecraft().objectMouseOver = new RayTraceResult(RayTraceResult.Type.MISS, vec3d3, (EnumFacing)null, new BlockPos(vec3d3));
	            }

	            if (pointedEntities.get(entity.getEntityId()) instanceof EntityPlayer == false && (d2 < d1 || Minecraft.getMinecraft().objectMouseOver == null))
	            {
	                Minecraft.getMinecraft().objectMouseOver = new RayTraceResult(pointedEntity, vec3d3);

	                if (pointedEntities.get(entity.getEntityId()) instanceof EntityLivingBase || pointedEntities.get(entity.getEntityId()) instanceof EntityItemFrame)
	                {
	                    Minecraft.getMinecraft().pointedEntity = pointedEntities.get(entity.getEntityId());
	                }
	            }

	            Minecraft.getMinecraft().mcProfiler.endSection();
	        }
	    }

	    if (pointedEntities.get(entity.getEntityId()) == null)
	    	return;
	    
	    deselectPreviousEntity(entity);
	    
	    previousEntities.put(entity.getEntityId(), pointedEntities.get(entity.getEntityId()));

	}
	
	public void deselectPreviousEntity(Entity player)
	{
		if (previousEntities.get(player.getEntityId()) == null)
			return;
		
		previousEntities.get(player.getEntityId()).setGlowing(false);
		previousEntities.put(player.getEntityId(), null);
	}
	
	public void deselectCurrentEntity(Entity player)
	{
		entityIDs.put(player.getEntityId(), 0);
		
		if (pointedEntities.get(player.getEntityId()) == null)
			return;
		
		pointedEntities.get(player.getEntityId()).setGlowing(false);
		pointedEntities.get(player.getEntityId()).noClip = false;
		pointedEntities.put(player.getEntityId(), null);
		
		if (previousEntities.get(player.getEntityId()) == null)
			return;
		
		previousEntities.get(player.getEntityId()).setGlowing(false);
		previousEntities.get(player.getEntityId()).noClip = false;
		previousEntities.put(player.getEntityId(), null);
		
	}

	
	public void computeLegacyTick(EntityPlayer player, boolean server)
	{
		
		if (previousEntity != null)
			previousEntity.setGlowing(false);
		
		this.player = player;

		if (player == null || KeyBindings.activateTelekinesis.isKeyDown() == false)
			return;
		
		if (!server)
		{	
			updatePointedEntity(player);
			
			if (pointedEntity == null)
				return;
			if (pointedEntity instanceof EntityPlayer)
				return;
			
			entityID = pointedEntity.getEntityId();	
		}
		else
		{	
			if (entityID == 0)
				return;
				
			pointedEntity = player.world.getEntityByID(entityID);
			
			if (pointedEntity == null)
				return;
			if (pointedEntity instanceof EntityPlayer)
				return;
			
		}
		
		if (pointedEntity == null)
			return;

		// Fix entity no-clipping
		Vec3d oldPosition = pointedEntity.getPositionVector();
		
		final float distance = 3f;
		Vec3d desiredPosition = new Vec3d(player.getLookVec().x * distance, player.getLookVec().y * distance + 1, player.getLookVec().z * distance).add(player.getPositionVector());
		pointedEntity.setPositionAndUpdate(desiredPosition.x, desiredPosition.y, desiredPosition.z);
		
		// Fix entity no-clipping
		if(pointedEntity.isInsideOfMaterial(Material.AIR) == false) // If inside of another block
			pointedEntity.setPositionAndUpdate(oldPosition.x, oldPosition.y, oldPosition.z); // Move back to last known working position
		
		if (pointedEntity == null)
			return;
		
		pointedEntity.fallDistance = 0f;
		pointedEntity.motionX = 0f;
		pointedEntity.motionY = 0f;
		pointedEntity.motionZ = 0f;
		
		if (previousEntity != null)
			previousEntity.setGlowing(true);
	
	}
	
	private double getEntityColliderSize(Entity entity)
	{
		AxisAlignedBB collider = pointedEntity.getEntityBoundingBox();
		
		if (collider == null)
			return 0;
		
		double diffX = collider.maxX - collider.minX;
		double diffY = collider.maxY - collider.minY;
		double diffZ = collider.maxZ - collider.minZ;
		double finalDifference = diffX * diffY * diffZ;
		return finalDifference;
	}
	
	public void launchEntity(EntityPlayer player, boolean server)
	{
		if (pointedEntity == null || pointedEntity instanceof EntityPlayer)
			return;
		
		// Client side - decide if there is the need to launch, and if so calculate parameters
		if (server == false)
		{
			launchRequired = KeyBindings.launchTelekinesis.isKeyDown();
			if (launchRequired)
			{
				final float magnitude = 0.3f;
				force = new Vec3d(player.getLookVec().x * magnitude, player.getLookVec().y * magnitude, player.getLookVec().z * magnitude);
				applyLaunchEffect();
			}
		}
		
		// Launch the entity if required
		if (server && launchRequired)
		{
			applyLaunchEffect();
			launchRequired = false;
			deselectCurrentEntity();
			deselectPreviousEntity();
		}
		
	}
	
	// Helper function to clean up code - executed on client and server to avoid inconsistencies
	private void applyLaunchEffect()
	{
		pointedEntity.fallDistance = 1000f;
		pointedEntity.addVelocity(force.x, force.y, force.z);
	}
	
	@Override
	public void computeLegacyTick(EntityPlayer player)
	{
		System.err.println("Error - you're calling the wrong method!");
	}

	@Override
	public void blessedMessage(EntityPlayer player)
	{
		if (player.world.isRemote)
			player.sendMessage(new TextComponentString(LEGACY_NAME).setStyle(new Style().setColor(TextFormatting.GOLD)));
	}
	*/
}
