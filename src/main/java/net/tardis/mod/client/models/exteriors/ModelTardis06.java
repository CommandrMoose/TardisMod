package net.tardis.mod.client.models.exteriors;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelTardis06 extends ModelBase implements IExteriorModel {
    private final ModelRenderer fullscaleEboxclosed;
    private final ModelRenderer Base;
    private final ModelRenderer CornerPosts;
    private final ModelRenderer LeftDoor;
    private final ModelRenderer RightDoor;
    private final ModelRenderer Outline;
    private final ModelRenderer UpperSignage;
    private final ModelRenderer Roof;
    private final ModelRenderer LeftSide;
    private final ModelRenderer BackSide;
    private final ModelRenderer RightSide;
    private final ModelRenderer octagon;
    private final ModelRenderer octagon2;
    private final ModelRenderer octagon3;

    public ModelTardis06() {

        textureWidth = 16;
        textureHeight = 16;

        fullscaleEboxclosed = new ModelRenderer(this);
        fullscaleEboxclosed.setRotationPoint(-8.0F, 16.0F, 8.0F);

        Base = new ModelRenderer(this);
        Base.setRotationPoint(0.0F, 0.0F, 0.0F);
        fullscaleEboxclosed.addChild(Base);
        Base.cubeList.add(new ModelBox(Base, 0, 0, -106.0F, 143.5F, -132.0F, 228, 5, 228, 0.0F, false));
        Base.cubeList.add(new ModelBox(Base, 0, 0, -114.5F, 149.0F, -140.5F, 245, 19, 245, 0.0F, false));
        Base.cubeList.add(new ModelBox(Base, 0, 0, -110.25F, 146.5F, -136.25F, 236, 2, 236, 0.0F, false));
        Base.cubeList.add(new ModelBox(Base, 0, 0, -2.0F, -82.0F, -18.0F, 10, 10, 10, 0.0F, false));

        CornerPosts = new ModelRenderer(this);
        CornerPosts.setRotationPoint(0.0F, 0.0F, 0.0F);
        fullscaleEboxclosed.addChild(CornerPosts);
        CornerPosts.cubeList.add(new ModelBox(CornerPosts, 0, 0, 95.0F, -233.5F, -130.0F, 25, 377, 25, 0.0F, false));
        CornerPosts.cubeList.add(new ModelBox(CornerPosts, 0, 0, -97.0F, -232.5F, -123.0F, 210, 4, 210, 0.0F, false));
        CornerPosts.cubeList.add(new ModelBox(CornerPosts, 0, 0, 97.5F, -196.5F, -131.0F, 22, 340, 23, 0.0F, false));
        CornerPosts.cubeList.add(new ModelBox(CornerPosts, 0, 0, 97.5F, -196.5F, -130.0F, 23, 340, 22, 0.0F, false));
        CornerPosts.cubeList.add(new ModelBox(CornerPosts, 0, 0, 108.5F, -225.5F, -131.0F, 11, 369, 1, 0.0F, false));
        CornerPosts.cubeList.add(new ModelBox(CornerPosts, 0, 0, 120.0F, -225.5F, -130.0F, 1, 369, 11, 0.0F, false));
        CornerPosts.cubeList.add(new ModelBox(CornerPosts, 0, 0, -104.0F, -233.5F, -130.0F, 25, 377, 25, 0.0F, false));
        CornerPosts.cubeList.add(new ModelBox(CornerPosts, 0, 0, -104.0F, -196.5F, -131.0F, 22, 340, 23, 0.0F, false));
        CornerPosts.cubeList.add(new ModelBox(CornerPosts, 0, 0, -104.0F, -225.5F, -131.0F, 11, 369, 1, 0.0F, false));
        CornerPosts.cubeList.add(new ModelBox(CornerPosts, 0, 0, -105.0F, -225.5F, -130.0F, 1, 369, 11, 0.0F, false));
        CornerPosts.cubeList.add(new ModelBox(CornerPosts, 0, 0, -105.0F, -196.5F, -130.0F, 23, 340, 22, 0.0F, false));
        CornerPosts.cubeList.add(new ModelBox(CornerPosts, 0, 0, 108.5F, -225.5F, 94.0F, 11, 369, 1, 0.0F, false));
        CornerPosts.cubeList.add(new ModelBox(CornerPosts, 0, 0, 95.0F, -233.5F, 69.0F, 25, 377, 25, 0.0F, false));
        CornerPosts.cubeList.add(new ModelBox(CornerPosts, 0, 0, 97.5F, -196.5F, 71.5F, 23, 340, 22, 0.0F, false));
        CornerPosts.cubeList.add(new ModelBox(CornerPosts, 0, 0, 120.0F, -225.5F, 82.5F, 1, 369, 11, 0.0F, false));
        CornerPosts.cubeList.add(new ModelBox(CornerPosts, 0, 0, 97.5F, -196.5F, 71.5F, 22, 340, 23, 0.0F, false));
        CornerPosts.cubeList.add(new ModelBox(CornerPosts, 0, 0, -104.0F, -196.5F, 71.5F, 22, 340, 23, 0.0F, false));
        CornerPosts.cubeList.add(new ModelBox(CornerPosts, 0, 0, -104.0F, -225.5F, 94.0F, 11, 369, 1, 0.0F, false));
        CornerPosts.cubeList.add(new ModelBox(CornerPosts, 0, 0, -104.0F, -233.5F, 69.0F, 25, 377, 25, 0.0F, false));
        CornerPosts.cubeList.add(new ModelBox(CornerPosts, 0, 0, -105.0F, -225.5F, 82.5F, 1, 369, 11, 0.0F, false));
        CornerPosts.cubeList.add(new ModelBox(CornerPosts, 0, 0, -105.0F, -196.5F, 71.5F, 23, 340, 22, 0.0F, false));
        CornerPosts.cubeList.add(new ModelBox(CornerPosts, 0, 0, 94.0F, -246.5F, -119.0F, 15, 14, 15, 0.0F, false));
        CornerPosts.cubeList.add(new ModelBox(CornerPosts, 0, 0, -93.0F, -246.5F, -119.0F, 15, 14, 15, 0.0F, false));
        CornerPosts.cubeList.add(new ModelBox(CornerPosts, 0, 0, -93.0F, -246.5F, 68.0F, 15, 14, 15, 0.0F, false));
        CornerPosts.cubeList.add(new ModelBox(CornerPosts, 0, 0, 94.0F, -246.5F, 68.0F, 15, 14, 15, 0.0F, false));

        LeftDoor = new ModelRenderer(this);
        LeftDoor.setRotationPoint(0.0F, 0.0F, 0.0F);
        fullscaleEboxclosed.addChild(LeftDoor);
        LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 0, -4.5F, -196.5F, -121.5F, 12, 340, 7, 0.0F, false));
        LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 0, -58.5F, -96.5F, -118.5F, 47, 56, 1, 0.0F, false));
        LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 0, -65.5F, -115.5F, -121.5F, 61, 12, 7, 0.0F, false));
        LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 0, -65.5F, -185.5F, -119.5F, 61, 70, 3, 0.0F, false));
        LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 0, -65.5F, -196.5F, -121.5F, 61, 11, 7, 0.0F, false));
        LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 0, -74.5F, -196.5F, -121.5F, 9, 340, 7, 0.0F, false));
        LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 0, -65.5F, -40.5F, -119.5F, 61, 7, 4, 0.0F, false));
        LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 0, -65.5F, -21.5F, -118.5F, 61, 70, 1, 0.0F, false));
        LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 0, -65.5F, 48.5F, -121.5F, 61, 12, 7, 0.0F, false));
        LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 0, -65.5F, 60.5F, -118.5F, 61, 70, 1, 0.0F, false));
        LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 0, -65.5F, 130.5F, -121.5F, 61, 13, 7, 0.0F, false));
        LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 0, 4.5F, -196.5F, -123.5F, 7, 340, 11, 0.0F, false));
        LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 0, -65.5F, -117.5F, -120.5F, 61, 2, 5, 0.0F, false));
        LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 0, -65.5F, -151.25F, -120.5F, 61, 2, 5, 0.0F, false));
        LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 0, -65.5F, -185.0F, -120.5F, 61, 2, 5, 0.0F, false));
        LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 0, -7.0F, -183.0F, -120.5F, 2, 65, 5, 0.0F, false));
        LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 0, -26.5F, -183.0F, -120.5F, 2, 65, 5, 0.0F, false));
        LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 0, -46.0F, -183.0F, -120.5F, 2, 65, 5, 0.0F, false));
        LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 0, -65.5F, -183.0F, -120.5F, 2, 65, 5, 0.0F, false));
        LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 0, -65.5F, -33.5F, -121.5F, 61, 12, 7, 0.0F, false));
        LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 0, -65.5F, -103.5F, -119.5F, 61, 7, 4, 0.0F, false));
        LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 0, -11.5F, -96.5F, -119.5F, 7, 56, 4, 0.0F, false));
        LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 0, -65.5F, -96.5F, -119.5F, 7, 56, 4, 0.0F, false));
        LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 0, -65.5F, -48.5F, -120.5F, 2, 5, 1, 0.0F, false));
        LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 0, -65.5F, -93.5F, -120.5F, 2, 5, 1, 0.0F, false));
        LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 0, -9.0F, -64.5F, -120.5F, 3, 5, 1, 0.0F, false));
        LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 0, -9.0F, -70.5F, -124.5F, 3, 4, 2, 0.0F, false));
        LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 0, -9.0F, -77.5F, -120.5F, 3, 5, 1, 0.0F, false));
        LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 0, -9.0F, -77.5F, -124.5F, 3, 7, 2, 0.0F, false));
        LeftDoor.cubeList.add(new ModelBox(LeftDoor, 0, 0, -9.0F, -66.5F, -124.5F, 3, 7, 2, 0.0F, false));

        RightDoor = new ModelRenderer(this);
        RightDoor.setRotationPoint(0.0F, 0.0F, 0.0F);
        fullscaleEboxclosed.addChild(RightDoor);
        RightDoor.cubeList.add(new ModelBox(RightDoor, 0, 0, 8.5F, -196.5F, -121.5F, 12, 340, 7, 0.0F, false));
        RightDoor.cubeList.add(new ModelBox(RightDoor, 0, 0, 20.5F, -103.5F, -118.5F, 61, 70, 1, 0.0F, false));
        RightDoor.cubeList.add(new ModelBox(RightDoor, 0, 0, 20.5F, -115.5F, -121.5F, 61, 12, 7, 0.0F, false));
        RightDoor.cubeList.add(new ModelBox(RightDoor, 0, 0, 20.5F, -185.5F, -119.5F, 61, 70, 3, 0.0F, false));
        RightDoor.cubeList.add(new ModelBox(RightDoor, 0, 0, 20.5F, -196.5F, -121.5F, 61, 11, 7, 0.0F, false));
        RightDoor.cubeList.add(new ModelBox(RightDoor, 0, 0, 81.5F, -196.5F, -121.5F, 9, 340, 7, 0.0F, false));
        RightDoor.cubeList.add(new ModelBox(RightDoor, 0, 0, 20.5F, -33.5F, -121.5F, 61, 12, 7, 0.0F, false));
        RightDoor.cubeList.add(new ModelBox(RightDoor, 0, 0, 20.5F, -21.5F, -118.5F, 61, 70, 1, 0.0F, false));
        RightDoor.cubeList.add(new ModelBox(RightDoor, 0, 0, 20.5F, 48.5F, -121.5F, 61, 12, 7, 0.0F, false));
        RightDoor.cubeList.add(new ModelBox(RightDoor, 0, 0, 20.5F, 60.5F, -118.5F, 61, 70, 1, 0.0F, false));
        RightDoor.cubeList.add(new ModelBox(RightDoor, 0, 0, 20.5F, 130.5F, -121.5F, 61, 13, 7, 0.0F, false));
        RightDoor.cubeList.add(new ModelBox(RightDoor, 0, 0, 20.5F, -185.0F, -120.5F, 61, 2, 5, 0.0F, false));
        RightDoor.cubeList.add(new ModelBox(RightDoor, 0, 0, 59.5F, -183.0F, -120.5F, 2, 65, 5, 0.0F, false));
        RightDoor.cubeList.add(new ModelBox(RightDoor, 0, 0, 79.0F, -183.0F, -120.5F, 2, 65, 5, 0.0F, false));
        RightDoor.cubeList.add(new ModelBox(RightDoor, 0, 0, 20.5F, -151.25F, -120.5F, 61, 2, 5, 0.0F, false));
        RightDoor.cubeList.add(new ModelBox(RightDoor, 0, 0, 40.0F, -183.0F, -120.5F, 2, 65, 5, 0.0F, false));
        RightDoor.cubeList.add(new ModelBox(RightDoor, 0, 0, 20.5F, -183.0F, -120.5F, 2, 65, 5, 0.0F, false));
        RightDoor.cubeList.add(new ModelBox(RightDoor, 0, 0, 20.5F, -117.5F, -120.5F, 61, 2, 5, 0.0F, false));
        RightDoor.cubeList.add(new ModelBox(RightDoor, 0, 0, 12.5F, -31.0F, -122.5F, 7, 7, 1, 0.0F, false));
        RightDoor.cubeList.add(new ModelBox(RightDoor, 0, 0, 13.0F, -30.5F, -123.5F, 6, 6, 1, 0.0F, false));
        RightDoor.cubeList.add(new ModelBox(RightDoor, 0, 0, 14.5F, -71.5F, -126.5F, 3, 6, 2, 0.0F, false));
        RightDoor.cubeList.add(new ModelBox(RightDoor, 0, 0, 14.5F, -78.5F, -122.5F, 3, 4, 1, 0.0F, false));
        RightDoor.cubeList.add(new ModelBox(RightDoor, 0, 0, 14.5F, -62.5F, -122.5F, 3, 4, 1, 0.0F, false));
        RightDoor.cubeList.add(new ModelBox(RightDoor, 0, 0, 41.0F, -78.5F, -119.5F, 20, 20, 1, 0.0F, false));
        RightDoor.cubeList.add(new ModelBox(RightDoor, 0, 0, 14.5F, -65.5F, -126.5F, 3, 6, 2, 0.0F, false));
        RightDoor.cubeList.add(new ModelBox(RightDoor, 0, 0, 14.5F, -77.5F, -126.5F, 3, 6, 2, 0.0F, false));

        Outline = new ModelRenderer(this);
        Outline.setRotationPoint(0.0F, 0.0F, 0.0F);
        fullscaleEboxclosed.addChild(Outline);
        Outline.cubeList.add(new ModelBox(Outline, 0, 0, 90.5F, -200.5F, -125.0F, 24, 344, 24, 0.0F, false));
        Outline.cubeList.add(new ModelBox(Outline, 0, 0, -99.0F, -200.5F, -125.0F, 214, 4, 214, 0.0F, false));
        Outline.cubeList.add(new ModelBox(Outline, 0, 0, -99.0F, -200.5F, -125.0F, 24, 344, 24, 0.0F, false));
        Outline.cubeList.add(new ModelBox(Outline, 0, 0, -99.0F, -200.5F, 64.5F, 24, 344, 24, 0.0F, false));
        Outline.cubeList.add(new ModelBox(Outline, 0, 0, 90.5F, -200.5F, 64.5F, 24, 344, 24, 0.0F, false));

        UpperSignage = new ModelRenderer(this);
        UpperSignage.setRotationPoint(0.0F, 0.0F, 0.0F);
        fullscaleEboxclosed.addChild(UpperSignage);
        UpperSignage.cubeList.add(new ModelBox(UpperSignage, 0, 0, 100.5F, -228.0F, -136.0F, 3, 27, 236, 0.0F, false));
        UpperSignage.cubeList.add(new ModelBox(UpperSignage, 0, 0, -84.5F, -204.0F, -136.0F, 185, 3, 236, 0.0F, false));
        UpperSignage.cubeList.add(new ModelBox(UpperSignage, 0, 0, -84.5F, -228.5F, -136.0F, 185, 3, 236, 0.0F, false));
        UpperSignage.cubeList.add(new ModelBox(UpperSignage, 0, 0, -84.5F, -225.0F, -134.0F, 185, 21, 232, 0.0F, false));
        UpperSignage.cubeList.add(new ModelBox(UpperSignage, 0, 0, -87.5F, -228.0F, -136.0F, 3, 27, 236, 0.0F, false));
        UpperSignage.cubeList.add(new ModelBox(UpperSignage, 0, 0, -110.0F, -228.0F, -113.5F, 236, 27, 3, 0.0F, false));
        UpperSignage.cubeList.add(new ModelBox(UpperSignage, 0, 0, -110.0F, -228.5F, -110.5F, 236, 3, 185, 0.0F, false));
        UpperSignage.cubeList.add(new ModelBox(UpperSignage, 0, 0, -110.0F, -228.0F, 74.5F, 236, 27, 3, 0.0F, false));
        UpperSignage.cubeList.add(new ModelBox(UpperSignage, 0, 0, -110.0F, -204.0F, -110.5F, 236, 3, 185, 0.0F, false));
        UpperSignage.cubeList.add(new ModelBox(UpperSignage, 0, 0, -108.0F, -225.0F, -110.5F, 232, 21, 185, 0.0F, false));

        Roof = new ModelRenderer(this);
        Roof.setRotationPoint(0.0F, 0.0F, 0.0F);
        fullscaleEboxclosed.addChild(Roof);
        Roof.cubeList.add(new ModelBox(Roof, 0, 0, -90.0F, -247.5F, -116.0F, 196, 15, 196, 0.0F, false));
        Roof.cubeList.add(new ModelBox(Roof, 0, 0, -84.5F, -266.5F, -110.5F, 185, 19, 185, 0.0F, false));
        Roof.cubeList.add(new ModelBox(Roof, 0, 0, -5.0F, -291.5F, -31.0F, 26, 10, 26, 0.0F, false));
        Roof.cubeList.add(new ModelBox(Roof, 0, 0, -79.5F, -267.5F, -105.5F, 175, 19, 175, 0.0F, false));
        Roof.cubeList.add(new ModelBox(Roof, 0, 0, -74.5F, -268.5F, -100.5F, 165, 20, 165, 0.0F, false));
        Roof.cubeList.add(new ModelBox(Roof, 0, 0, -69.5F, -269.5F, -95.5F, 155, 21, 155, 0.0F, false));
        Roof.cubeList.add(new ModelBox(Roof, 0, 0, -64.5F, -270.5F, -90.5F, 145, 22, 145, 0.0F, false));
        Roof.cubeList.add(new ModelBox(Roof, 0, 0, -59.5F, -271.5F, -85.5F, 135, 23, 135, 0.0F, false));
        Roof.cubeList.add(new ModelBox(Roof, 0, 0, -54.5F, -272.5F, -80.5F, 125, 24, 125, 0.0F, false));
        Roof.cubeList.add(new ModelBox(Roof, 0, 0, -49.5F, -273.5F, -75.5F, 115, 25, 115, 0.0F, false));
        Roof.cubeList.add(new ModelBox(Roof, 0, 0, -44.5F, -274.5F, -70.5F, 105, 26, 105, 0.0F, false));
        Roof.cubeList.add(new ModelBox(Roof, 0, 0, -39.5F, -275.5F, -65.5F, 95, 27, 95, 0.0F, false));
        Roof.cubeList.add(new ModelBox(Roof, 0, 0, -34.5F, -276.5F, -60.5F, 85, 28, 85, 0.0F, false));
        Roof.cubeList.add(new ModelBox(Roof, 0, 0, -29.5F, -277.5F, -55.5F, 75, 29, 75, 0.0F, false));
        Roof.cubeList.add(new ModelBox(Roof, 0, 0, -24.5F, -278.5F, -50.5F, 65, 30, 65, 0.0F, false));
        Roof.cubeList.add(new ModelBox(Roof, 0, 0, -19.5F, -279.5F, -45.5F, 55, 31, 55, 0.0F, false));
        Roof.cubeList.add(new ModelBox(Roof, 0, 0, -14.5F, -280.5F, -40.5F, 45, 32, 45, 0.0F, false));
        Roof.cubeList.add(new ModelBox(Roof, 0, 0, -9.5F, -281.5F, -35.5F, 35, 33, 35, 0.0F, false));
        Roof.cubeList.add(new ModelBox(Roof, 0, 0, 6.0F, -326.5F, -20.0F, 4, 5, 4, 0.0F, false));

        LeftSide = new ModelRenderer(this);
        LeftSide.setRotationPoint(0.0F, 0.0F, 0.0F);
        fullscaleEboxclosed.addChild(LeftSide);
        LeftSide.cubeList.add(new ModelBox(LeftSide, 0, 0, -93.5F, -185.5F, -5.5F, 3, 70, 61, 0.0F, false));
        LeftSide.cubeList.add(new ModelBox(LeftSide, 0, 0, -95.5F, -196.5F, 55.5F, 7, 340, 9, 0.0F, false));
        LeftSide.cubeList.add(new ModelBox(LeftSide, 0, 0, -92.5F, -21.5F, -5.5F, 1, 70, 61, 0.0F, false));
        LeftSide.cubeList.add(new ModelBox(LeftSide, 0, 0, -92.5F, 60.5F, -5.5F, 1, 70, 61, 0.0F, false));
        LeftSide.cubeList.add(new ModelBox(LeftSide, 0, 0, -97.5F, -196.5F, -21.5F, 11, 340, 7, 0.0F, false));
        LeftSide.cubeList.add(new ModelBox(LeftSide, 0, 0, -94.5F, -183.0F, 14.0F, 5, 65, 2, 0.0F, false));
        LeftSide.cubeList.add(new ModelBox(LeftSide, 0, 0, -94.5F, -183.0F, 33.5F, 5, 65, 2, 0.0F, false));
        LeftSide.cubeList.add(new ModelBox(LeftSide, 0, 0, -94.5F, -183.0F, 53.0F, 5, 65, 2, 0.0F, false));
        LeftSide.cubeList.add(new ModelBox(LeftSide, 0, 0, -92.5F, -103.5F, -5.5F, 1, 70, 61, 0.0F, false));
        LeftSide.cubeList.add(new ModelBox(LeftSide, 0, 0, -95.5F, -196.5F, -30.5F, 7, 340, 25, 0.0F, false));
        LeftSide.cubeList.add(new ModelBox(LeftSide, 0, 0, -95.5F, -115.5F, -91.5F, 7, 12, 147, 0.0F, false));
        LeftSide.cubeList.add(new ModelBox(LeftSide, 0, 0, -93.5F, -185.5F, -91.5F, 3, 70, 61, 0.0F, false));
        LeftSide.cubeList.add(new ModelBox(LeftSide, 0, 0, -95.5F, -196.5F, -91.5F, 7, 11, 147, 0.0F, false));
        LeftSide.cubeList.add(new ModelBox(LeftSide, 0, 0, -95.5F, -196.5F, -100.5F, 7, 340, 9, 0.0F, false));
        LeftSide.cubeList.add(new ModelBox(LeftSide, 0, 0, -92.5F, -21.5F, -91.5F, 1, 70, 61, 0.0F, false));
        LeftSide.cubeList.add(new ModelBox(LeftSide, 0, 0, -95.5F, 48.5F, -91.5F, 7, 12, 147, 0.0F, false));
        LeftSide.cubeList.add(new ModelBox(LeftSide, 0, 0, -92.5F, 60.5F, -91.5F, 1, 70, 61, 0.0F, false));
        LeftSide.cubeList.add(new ModelBox(LeftSide, 0, 0, -95.5F, 130.5F, -91.5F, 7, 13, 147, 0.0F, false));
        LeftSide.cubeList.add(new ModelBox(LeftSide, 0, 0, -94.5F, -117.5F, -91.5F, 5, 2, 147, 0.0F, false));
        LeftSide.cubeList.add(new ModelBox(LeftSide, 0, 0, -94.5F, -151.25F, -91.5F, 5, 2, 147, 0.0F, false));
        LeftSide.cubeList.add(new ModelBox(LeftSide, 0, 0, -94.5F, -185.0F, -91.5F, 5, 2, 147, 0.0F, false));
        LeftSide.cubeList.add(new ModelBox(LeftSide, 0, 0, -94.5F, -183.0F, -33.0F, 5, 65, 30, 0.0F, false));
        LeftSide.cubeList.add(new ModelBox(LeftSide, 0, 0, -94.5F, -183.0F, -52.5F, 5, 65, 2, 0.0F, false));
        LeftSide.cubeList.add(new ModelBox(LeftSide, 0, 0, -94.5F, -183.0F, -72.0F, 5, 65, 2, 0.0F, false));
        LeftSide.cubeList.add(new ModelBox(LeftSide, 0, 0, -94.5F, -183.0F, -91.5F, 5, 65, 2, 0.0F, false));
        LeftSide.cubeList.add(new ModelBox(LeftSide, 0, 0, -95.5F, -33.5F, -91.5F, 7, 12, 147, 0.0F, false));
        LeftSide.cubeList.add(new ModelBox(LeftSide, 0, 0, -92.5F, -103.5F, -91.5F, 1, 70, 61, 0.0F, false));

        BackSide = new ModelRenderer(this);
        BackSide.setRotationPoint(0.0F, 0.0F, 0.0F);
        fullscaleEboxclosed.addChild(BackSide);
        BackSide.cubeList.add(new ModelBox(BackSide, 0, 0, 20.5F, -185.5F, 80.5F, 61, 70, 3, 0.0F, false));
        BackSide.cubeList.add(new ModelBox(BackSide, 0, 0, 81.5F, -196.5F, 78.5F, 9, 340, 7, 0.0F, false));
        BackSide.cubeList.add(new ModelBox(BackSide, 0, 0, 20.5F, -21.5F, 81.5F, 61, 70, 1, 0.0F, false));
        BackSide.cubeList.add(new ModelBox(BackSide, 0, 0, 20.5F, 60.5F, 81.5F, 61, 70, 1, 0.0F, false));
        BackSide.cubeList.add(new ModelBox(BackSide, 0, 0, 4.5F, -196.5F, 76.5F, 7, 340, 11, 0.0F, false));
        BackSide.cubeList.add(new ModelBox(BackSide, 0, 0, 40.0F, -183.0F, 79.5F, 2, 65, 5, 0.0F, false));
        BackSide.cubeList.add(new ModelBox(BackSide, 0, 0, 59.5F, -183.0F, 79.5F, 2, 65, 5, 0.0F, false));
        BackSide.cubeList.add(new ModelBox(BackSide, 0, 0, 79.0F, -183.0F, 79.5F, 2, 65, 5, 0.0F, false));
        BackSide.cubeList.add(new ModelBox(BackSide, 0, 0, 20.5F, -103.5F, 81.5F, 61, 70, 1, 0.0F, false));
        BackSide.cubeList.add(new ModelBox(BackSide, 0, 0, -4.5F, -196.5F, 78.5F, 25, 340, 7, 0.0F, false));
        BackSide.cubeList.add(new ModelBox(BackSide, 0, 0, -65.5F, -115.5F, 78.5F, 147, 12, 7, 0.0F, false));
        BackSide.cubeList.add(new ModelBox(BackSide, 0, 0, -65.5F, -185.5F, 80.5F, 61, 70, 3, 0.0F, false));
        BackSide.cubeList.add(new ModelBox(BackSide, 0, 0, -65.5F, -196.5F, 78.5F, 147, 11, 7, 0.0F, false));
        BackSide.cubeList.add(new ModelBox(BackSide, 0, 0, -74.5F, -196.5F, 78.5F, 9, 340, 7, 0.0F, false));
        BackSide.cubeList.add(new ModelBox(BackSide, 0, 0, -65.5F, -21.5F, 81.5F, 61, 70, 1, 0.0F, false));
        BackSide.cubeList.add(new ModelBox(BackSide, 0, 0, -65.5F, 48.5F, 78.5F, 147, 12, 7, 0.0F, false));
        BackSide.cubeList.add(new ModelBox(BackSide, 0, 0, -65.5F, 60.5F, 81.5F, 61, 70, 1, 0.0F, false));
        BackSide.cubeList.add(new ModelBox(BackSide, 0, 0, -65.5F, 130.5F, 78.5F, 147, 13, 7, 0.0F, false));
        BackSide.cubeList.add(new ModelBox(BackSide, 0, 0, -65.5F, -117.5F, 79.5F, 147, 2, 5, 0.0F, false));
        BackSide.cubeList.add(new ModelBox(BackSide, 0, 0, -65.5F, -151.25F, 79.5F, 147, 2, 5, 0.0F, false));
        BackSide.cubeList.add(new ModelBox(BackSide, 0, 0, -65.5F, -185.0F, 79.5F, 147, 2, 5, 0.0F, false));
        BackSide.cubeList.add(new ModelBox(BackSide, 0, 0, -7.0F, -183.0F, 79.5F, 30, 65, 5, 0.0F, false));
        BackSide.cubeList.add(new ModelBox(BackSide, 0, 0, -26.5F, -183.0F, 79.5F, 2, 65, 5, 0.0F, false));
        BackSide.cubeList.add(new ModelBox(BackSide, 0, 0, -46.0F, -183.0F, 79.5F, 2, 65, 5, 0.0F, false));
        BackSide.cubeList.add(new ModelBox(BackSide, 0, 0, -65.5F, -183.0F, 79.5F, 2, 65, 5, 0.0F, false));
        BackSide.cubeList.add(new ModelBox(BackSide, 0, 0, -65.5F, -33.5F, 78.5F, 147, 12, 7, 0.0F, false));
        BackSide.cubeList.add(new ModelBox(BackSide, 0, 0, -65.5F, -103.5F, 81.5F, 61, 70, 1, 0.0F, false));

        RightSide = new ModelRenderer(this);
        RightSide.setRotationPoint(0.0F, 0.0F, 0.0F);
        fullscaleEboxclosed.addChild(RightSide);
        RightSide.cubeList.add(new ModelBox(RightSide, 0, 0, 106.5F, -185.5F, -91.5F, 3, 70, 61, 0.0F, false));
        RightSide.cubeList.add(new ModelBox(RightSide, 0, 0, 104.5F, -196.5F, -100.5F, 7, 340, 9, 0.0F, false));
        RightSide.cubeList.add(new ModelBox(RightSide, 0, 0, 107.5F, -21.5F, -91.5F, 1, 70, 61, 0.0F, false));
        RightSide.cubeList.add(new ModelBox(RightSide, 0, 0, 107.5F, 60.5F, -91.5F, 1, 70, 61, 0.0F, false));
        RightSide.cubeList.add(new ModelBox(RightSide, 0, 0, 102.5F, -196.5F, -21.5F, 11, 340, 7, 0.0F, false));
        RightSide.cubeList.add(new ModelBox(RightSide, 0, 0, 105.5F, -183.0F, -52.5F, 5, 65, 2, 0.0F, false));
        RightSide.cubeList.add(new ModelBox(RightSide, 0, 0, 105.5F, -183.0F, -72.0F, 5, 65, 2, 0.0F, false));
        RightSide.cubeList.add(new ModelBox(RightSide, 0, 0, 105.5F, -183.0F, -91.5F, 5, 65, 2, 0.0F, false));
        RightSide.cubeList.add(new ModelBox(RightSide, 0, 0, 107.5F, -103.5F, -91.5F, 1, 70, 61, 0.0F, false));
        RightSide.cubeList.add(new ModelBox(RightSide, 0, 0, 104.5F, -196.5F, -30.5F, 7, 340, 25, 0.0F, false));
        RightSide.cubeList.add(new ModelBox(RightSide, 0, 0, 104.5F, -115.5F, -91.5F, 7, 12, 147, 0.0F, false));
        RightSide.cubeList.add(new ModelBox(RightSide, 0, 0, 106.5F, -185.5F, -5.5F, 3, 70, 61, 0.0F, false));
        RightSide.cubeList.add(new ModelBox(RightSide, 0, 0, 104.5F, -196.5F, -91.5F, 7, 11, 147, 0.0F, false));
        RightSide.cubeList.add(new ModelBox(RightSide, 0, 0, 104.5F, -196.5F, 55.5F, 7, 340, 9, 0.0F, false));
        RightSide.cubeList.add(new ModelBox(RightSide, 0, 0, 107.5F, -21.5F, -5.5F, 1, 70, 61, 0.0F, false));
        RightSide.cubeList.add(new ModelBox(RightSide, 0, 0, 104.5F, 48.5F, -91.5F, 7, 12, 147, 0.0F, false));
        RightSide.cubeList.add(new ModelBox(RightSide, 0, 0, 107.5F, 60.5F, -5.5F, 1, 70, 61, 0.0F, false));
        RightSide.cubeList.add(new ModelBox(RightSide, 0, 0, 104.5F, 130.5F, -91.5F, 7, 13, 147, 0.0F, false));
        RightSide.cubeList.add(new ModelBox(RightSide, 0, 0, 105.5F, -117.5F, -91.5F, 5, 2, 147, 0.0F, false));
        RightSide.cubeList.add(new ModelBox(RightSide, 0, 0, 105.5F, -151.25F, -91.5F, 5, 2, 147, 0.0F, false));
        RightSide.cubeList.add(new ModelBox(RightSide, 0, 0, 105.5F, -185.0F, -91.5F, 5, 2, 147, 0.0F, false));
        RightSide.cubeList.add(new ModelBox(RightSide, 0, 0, 105.5F, -183.0F, -33.0F, 5, 65, 30, 0.0F, false));
        RightSide.cubeList.add(new ModelBox(RightSide, 0, 0, 105.5F, -183.0F, 14.0F, 5, 65, 2, 0.0F, false));
        RightSide.cubeList.add(new ModelBox(RightSide, 0, 0, 105.5F, -183.0F, 33.5F, 5, 65, 2, 0.0F, false));
        RightSide.cubeList.add(new ModelBox(RightSide, 0, 0, 105.5F, -183.0F, 53.0F, 5, 65, 2, 0.0F, false));
        RightSide.cubeList.add(new ModelBox(RightSide, 0, 0, 104.5F, -33.5F, -91.5F, 7, 12, 147, 0.0F, false));
        RightSide.cubeList.add(new ModelBox(RightSide, 0, 0, 107.5F, -103.5F, -5.5F, 1, 70, 61, 0.0F, false));

        octagon = new ModelRenderer(this);
        octagon.setRotationPoint(0.0F, 0.0F, 0.0F);
        fullscaleEboxclosed.addChild(octagon);
        octagon.cubeList.add(new ModelBox(octagon, 0, 0, 4.8934F, -324.5F, -25.5F, 6, 3, 15, 0.0F, false));
        octagon.cubeList.add(new ModelBox(octagon, 0, 0, 4.8934F, -324.5F, -25.5F, 6, 3, 15, 0.0F, false));
        octagon.cubeList.add(new ModelBox(octagon, 0, 0, 0.5F, -324.5F, -21.1066F, 15, 3, 6, 0.0F, false));
        octagon.cubeList.add(new ModelBox(octagon, 0, 0, 0.5F, -324.5F, -21.1066F, 15, 3, 6, 0.0F, false));

        octagon2 = new ModelRenderer(this);
        octagon2.setRotationPoint(0.0F, 0.0F, 0.0F);
        fullscaleEboxclosed.addChild(octagon2);
        octagon2.cubeList.add(new ModelBox(octagon2, 0, 0, 3.4437F, -308.5F, -29.0F, 9, 5, 22, 0.0F, false));
        octagon2.cubeList.add(new ModelBox(octagon2, 0, 0, 3.4437F, -308.5F, -29.0F, 9, 5, 22, 0.0F, false));
        octagon2.cubeList.add(new ModelBox(octagon2, 0, 0, -3.0F, -308.5F, -22.5563F, 22, 5, 9, 0.0F, false));
        octagon2.cubeList.add(new ModelBox(octagon2, 0, 0, -3.0F, -308.5F, -22.5563F, 22, 5, 9, 0.0F, false));

        octagon3 = new ModelRenderer(this);
        octagon3.setRotationPoint(0.0F, 0.0F, 0.0F);
        fullscaleEboxclosed.addChild(octagon3);
        octagon3.cubeList.add(new ModelBox(octagon3, 0, 0, 3.4437F, -314.0F, -29.0F, 9, 2, 22, 0.0F, false));
        octagon3.cubeList.add(new ModelBox(octagon3, 0, 0, 3.4437F, -314.0F, -29.0F, 9, 2, 22, 0.0F, false));
        octagon3.cubeList.add(new ModelBox(octagon3, 0, 0, -3.0F, -314.0F, -22.5563F, 22, 2, 9, 0.0F, false));
        octagon3.cubeList.add(new ModelBox(octagon3, 0, 0, -3.0F, -314.0F, -22.5563F, 22, 2, 9, 0.0F, false));
        octagon3.cubeList.add(new ModelBox(octagon3, 0, 0, 3.4437F, -299.5F, -29.0F, 9, 2, 22, 0.0F, false));
        octagon3.cubeList.add(new ModelBox(octagon3, 0, 0, 3.4437F, -299.5F, -29.0F, 9, 2, 22, 0.0F, false));
        octagon3.cubeList.add(new ModelBox(octagon3, 0, 0, -3.0F, -299.5F, -22.5563F, 22, 2, 9, 0.0F, false));
        octagon3.cubeList.add(new ModelBox(octagon3, 0, 0, -3.0F, -299.5F, -22.5563F, 22, 2, 9, 0.0F, false));

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GlStateManager.pushMatrix();
        GlStateManager.scale(0.1,0.1,0.1);
        fullscaleEboxclosed.render(f5);
        GlStateManager.popMatrix();
    }
    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void renderClosed(float scale) {
        this.render(null,0,0,0,0,0,scale);
    }

    @Override
    public void renderOpen(float scale) {
        this.render(null,0,0,0,0,0,scale);
    }
}