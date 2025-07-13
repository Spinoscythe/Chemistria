package io.github.spinoscythe.chemistria.block.entity;

import net.neoforged.neoforge.fluids.FluidStack;

public interface FluidMachine {
    FluidStack getFluid();

    void setFluid(FluidStack fluid);
}
