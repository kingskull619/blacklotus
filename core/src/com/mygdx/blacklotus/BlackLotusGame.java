package com.mygdx.blacklotus;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.blacklotus.screens.AbstractScreen;
import com.mygdx.blacklotus.screens.GameScreen;
import com.mygdx.blacklotus.screens.MenuScreen;

public class BlackLotusGame extends Game {
    public SpriteBatch batch;
    public AbstractScreen MENUSCREEN;
    public boolean isSoundEnabled;
	
	@Override
	public void create () {
        batch = new SpriteBatch();
        isSoundEnabled = true;
        MENUSCREEN = new MenuScreen(this);
        setScreen(MENUSCREEN);
	}

    @Override
    public void dispose() {
        super.dispose();
        MENUSCREEN.dispose();
    }
}
