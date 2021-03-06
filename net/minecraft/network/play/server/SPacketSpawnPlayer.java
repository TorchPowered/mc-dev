package net.minecraft.network.play.server;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.INetHandlerPlayClient;

public class SPacketSpawnPlayer implements Packet<INetHandlerPlayClient>
{
    private int entityId;
    private UUID playerId;
    private double x;
    private double y;
    private double z;
    private byte yaw;
    private byte pitch;
    private EntityDataManager watcher;
    private List < EntityDataManager.DataEntry<? >> dataManagerEntries;

    public SPacketSpawnPlayer()
    {
    }

    public SPacketSpawnPlayer(EntityPlayer p_i46971_1_)
    {
        this.entityId = p_i46971_1_.getEntityId();
        this.playerId = p_i46971_1_.getGameProfile().getId();
        this.x = p_i46971_1_.posX;
        this.y = p_i46971_1_.posY;
        this.z = p_i46971_1_.posZ;
        this.yaw = (byte)((int)(p_i46971_1_.rotationYaw * 256.0F / 360.0F));
        this.pitch = (byte)((int)(p_i46971_1_.rotationPitch * 256.0F / 360.0F));
        this.watcher = p_i46971_1_.getDataManager();
    }

    /**
     * Reads the raw packet data from the data stream.
     */
    public void readPacketData(PacketBuffer buf) throws IOException
    {
        this.entityId = buf.readVarIntFromBuffer();
        this.playerId = buf.readUuid();
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
        this.yaw = buf.readByte();
        this.pitch = buf.readByte();
        this.dataManagerEntries = EntityDataManager.readEntries(buf);
    }

    /**
     * Writes the raw packet data to the data stream.
     */
    public void writePacketData(PacketBuffer buf) throws IOException
    {
        buf.writeVarIntToBuffer(this.entityId);
        buf.writeUuid(this.playerId);
        buf.writeDouble(this.x);
        buf.writeDouble(this.y);
        buf.writeDouble(this.z);
        buf.writeByte(this.yaw);
        buf.writeByte(this.pitch);
        this.watcher.writeEntries(buf);
    }

    /**
     * Passes this Packet on to the NetHandler for processing.
     */
    public void processPacket(INetHandlerPlayClient handler)
    {
        handler.handleSpawnPlayer(this);
    }
}
