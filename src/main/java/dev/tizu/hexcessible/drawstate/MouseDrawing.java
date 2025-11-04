package dev.tizu.hexcessible.drawstate;

import java.util.HashMap;
import java.util.Map;

import dev.tizu.hexcessible.Hexcessible;
import dev.tizu.hexcessible.accessor.CastRef;
import dev.tizu.hexcessible.accessor.CastingInterfaceAccessor;
import dev.tizu.hexcessible.accessor.CastingInterfaceAccessor.State;
import net.minecraft.client.gui.DrawContext;

public final class MouseDrawing extends DrawState {
    private CastingInterfaceAccessor accessor;

    public MouseDrawing(CastRef castref, CastingInterfaceAccessor accessor) {
        super(castref);
        this.accessor = accessor;
    }

    @Override
    public void onRender(DrawContext ctx, int mx, int my) {
        if (accessor.getState() != State.DRAWING)
            return;
        var sig = accessor.getPattern().getAngles();
        var size = (int) castref.hexSize() * 2;
        KeyboardDrawing.render(ctx, mx + size, my, sig, false,
                Hexcessible.cfg().mouseDraw.tooltip, 0);
    }

    @Override
    public void requestExit() {
        super.requestExit();
        castref.closeUI();
    }

    @Override
    public Map<String, String> getHints() {
        var keys = new HashMap<String, String>();

        keys.put("lmb", "cast");

        return keys;
    }
}