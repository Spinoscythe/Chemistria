package io.github.spinoscythe.chemistria.util;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.spinoscythe.chemistria.client.util.FourVec2;
import net.minecraft.world.phys.Vec2;

public final class MathUtils {

    private MathUtils() {
    }

    public static FourVec2 getFourVec(PoseStack stack, float x1, float y1, float x2, float y2, float width) {
        final var start = new Vec2(x1, y1);
        final var end = new Vec2(x2, y2);

        final var vector = end.add(start.negated()).normalized();
        final var side = new Vec2(-vector.y, vector.x); // vector.perpendicular()

        final var pt1 = start.add(side.scale(width).negated());
        final var pt2 = end.add(side.scale(width).negated());
        final var pt3 = end.add(side.scale(width));
        final var pt4 = start.add(side.scale(width));

        return new FourVec2(pt1, pt2, pt3, pt4);
    }

    public static boolean isMouseWithinArea(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;
    }

    public static double mapNumber(double value, double rangeMin, double rangeMax, double resultMin, double resultMax) {
        return (value - rangeMin) / (rangeMax - rangeMin) * (resultMax - resultMin) + resultMin;
    }

    public static int mapToInt(double value, double rangeMin, double rangeMax, double resultMin, double resultMax) {
        return (int) mapNumber(value, rangeMin, rangeMax, resultMin, resultMax);
    }
}
