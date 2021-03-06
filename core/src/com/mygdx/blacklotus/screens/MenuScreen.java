package com.mygdx.blacklotus.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.blacklotus.BlackLotusGame;

/**
 * Created by Usuario on 09/12/2014.
 */
public class MenuScreen extends AbstractScreen {

    private Skin skin;
    private Table table;
    private SpriteBatch batch;
    private Stage stage;
    private Music music;
    private Texture fondo;

    public MenuScreen(BlackLotusGame main) {
        super(main);
        this.skin = new Skin(Gdx.files.internal("uiskin.json"));
        this.stage = new Stage();

        this.table = new Table();
    }

    @Override
    public void show() {
        batch = main.batch;
        music = Gdx.audio.newMusic(Gdx.files.internal("Ninjas_music.mp3"));
        music.setLooping(true);
        fondo = new Texture(Gdx.files.internal("fondoMenu.jpg"));

        if (main.isSoundEnabled)
            music.play();

        final TextButton play = new TextButton("Play", this.skin);
        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                final BlackLotusGame game = main;
                game.setScreen(new GameScreen(main));
                music.stop();
                stage.dispose();
            }
        });

        TextButton options = new TextButton("Options", this.skin);
        options.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                final BlackLotusGame game = main;
                game.setScreen(new OptionScreen(main, music));
                stage.dispose();
            }
        });

        TextButton board = new TextButton("HigScore", this.skin);
        board.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                final BlackLotusGame game = main;
                game.setScreen(new HigsCoreScreen(main, music));
                stage.dispose();
            }
        });

        super.show();
        table.setFillParent(true);

        table.row().padTop(Gdx.graphics.getHeight()/4);
        table.add(play).prefWidth(300);

        table.row().padTop(10);
        table.add(board).prefWidth(300);

        table.row().padTop(10);
        table.add(options).prefWidth(300);

        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(fondo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act(delta);
        stage.draw();

    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
    }
}
