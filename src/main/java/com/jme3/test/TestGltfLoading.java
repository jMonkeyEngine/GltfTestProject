/*
 * Copyright (c) 2009-2012 jMonkeyEngine
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'jMonkeyEngine' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.jme3.test;

import com.jme3.animation.AnimControl;
import com.jme3.animation.SkeletonControl;
import com.jme3.app.ChaseCameraAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.*;
import com.jme3.renderer.Limits;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.debug.custom.SkeletonDebugAppState;

import java.util.ArrayList;
import java.util.List;

public class TestGltfLoading extends SimpleApplication {

    Node autoRotate = new Node("autoRotate");
    List<Spatial> assets = new ArrayList<>();
    Node probeNode;
    float time = 0;
    int assetIndex = 0;
    boolean useAutoRotate = false;
    private final static String indentString = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
    int duration = 2;
    boolean playAnim = true;

    public static void main(String[] args) {
        TestGltfLoading app = new TestGltfLoading();
        app.start();
    }

    public void simpleInitApp() {

        SkeletonDebugAppState skeletonDebugAppState = new SkeletonDebugAppState();
        getStateManager().attach(skeletonDebugAppState);

        // cam.setLocation(new Vector3f(4.0339394f, 2.645184f, 6.4627485f));
        // cam.setRotation(new Quaternion(-0.013950467f, 0.98604023f, -0.119502485f, -0.11510504f));
        cam.setFrustumPerspective(45f, (float) cam.getWidth() / cam.getHeight(), 0.1f, 100f);
        renderer.setDefaultAnisotropicFilter(Math.min(renderer.getLimits().get(Limits.TextureAnisotropy), 8));
        setPauseOnLostFocus(false);

        flyCam.setMoveSpeed(5);
        flyCam.setDragToRotate(true);
        flyCam.setEnabled(false);
        viewPort.setBackgroundColor(new ColorRGBA().setAsSrgb(0.2f, 0.2f, 0.2f, 1.0f));
        rootNode.attachChild(autoRotate);
        probeNode = (Node) assetManager.loadModel("Scenes/defaultProbe.j3o");
        autoRotate.attachChild(probeNode);

//        DirectionalLight dl = new DirectionalLight();
//        dl.setDirection(new Vector3f(-1f, -1.0f, -1f).normalizeLocal());
//        dl.setColor(new ColorRGBA(1f, 1f, 1f, 1.0f));
//        rootNode.addLight(dl);
//
//        DirectionalLight dl2 = new DirectionalLight();
//        dl2.setDirection(new Vector3f(1f, 1.0f, 1f).normalizeLocal());
//        dl2.setColor(new ColorRGBA(0.5f, 0.5f, 0.5f, 1.0f));
//        rootNode.addLight(dl2);

//        PointLight pl = new PointLight(new Vector3f(5.0f, 5.0f, 5.0f), ColorRGBA.White, 30);
//        rootNode.addLight(pl);
//        PointLight pl1 = new PointLight(new Vector3f(-5.0f, -5.0f, -5.0f), ColorRGBA.White.mult(0.5f), 50);
//        rootNode.addLight(pl1);

//        loadModel("Models/gltf/box/box.gltf", Vector3f.ZERO, 1);
//        loadModel("Models/gltf/duck/Duck.gltf", new Vector3f(0, -1, 0), 1);
//        loadModel("Models/gltf/damagedHelmet/damagedHelmet.gltf", Vector3f.ZERO, 1);
//        loadModel("Models/gltf/hornet/scene.gltf", new Vector3f(0, -0.5f, 0), 0.4f);
//        loadModel("Models/gltf/adamHead/adamHead.gltf", Vector3f.ZERO, 0.6f);
        //loadModel("Models/gltf/busterDrone/busterDrone.gltf", new Vector3f(0, 0f, 0), 0.8f);
        //loadModel("Models/gltf/animatedCube/AnimatedCube.gltf", Vector3f.ZERO, 0.5f);
        //loadModel("Models/gltf/BoxAnimated/BoxAnimated.gltf", new Vector3f(0, 0f, 0), 0.8f);
        //loadModel("Models/gltf/april_may/scene.gltf", new Vector3f(0, -1f, 0), 0.01f);
        //loadModel("Models/gltf/BrainStem/BrainStem.gltf", new Vector3f(0, -1, 0), 1f);
        //loadModel("Models/gltf/RiggedFigure/RiggedSimple.gltf", new Vector3f(0, -0.3f, 0), 0.2f);
        loadModel("Models/gltf/RiggedFigure/RiggedFigure.gltf", new Vector3f(0, -0.3f, 0), 1f);
        //loadModel("Models/gltf/RiggedFigure/WalkingLady.gltf", new Vector3f(0, -0.f, 0), 1f);
        //loadModel("Models/Jaime/Jaime.j3o", new Vector3f(0, -1, 0), 1f);

        probeNode.attachChild(assets.get(0));

        ChaseCameraAppState chaseCam = new ChaseCameraAppState();
        chaseCam.setTarget(probeNode);
        getStateManager().attach(chaseCam);
        chaseCam.setInvertHorizontalAxis(true);
        chaseCam.setInvertVerticalAxis(true);
        chaseCam.setZoomSpeed(0.5f);
        chaseCam.setMinVerticalRotation(-FastMath.HALF_PI);
        chaseCam.setRotationSpeed(3);
        chaseCam.setDefaultDistance(3);
        chaseCam.setDefaultVerticalRotation(0.3f);

        inputManager.addMapping("autorotate", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addListener(new ActionListener() {
            @Override
            public void onAction(String name, boolean isPressed, float tpf) {
                if (isPressed) {
                    useAutoRotate = !useAutoRotate;
                }
            }
        }, "autorotate");

        // dumpScene(rootNode, 0);
    }

    private SkeletonControl findSkeletonControl(Spatial s) {
        SkeletonControl ctrl = s.getControl(SkeletonControl.class);
        if (ctrl != null) {
            return ctrl;
        }
        if (s instanceof Node) {
            Node n = (Node) s;
            for (Spatial spatial : n.getChildren()) {
                ctrl = findSkeletonControl(spatial);
                if (ctrl != null) {
                    return ctrl;
                }
            }
        }
        return null;
    }

    private void loadModel(String path, Vector3f offset, float scale) {
        Spatial s = assetManager.loadModel(path);
        s.scale(scale);
        s.move(offset);
        assets.add(s);
        if (playAnim) {
            playFirstAnim(s);
        }

        SkeletonControl ctrl = findSkeletonControl(s);
        if (ctrl == null) {
            return;
        }
        getStateManager().getState(SkeletonDebugAppState.class).addSkeleton(ctrl, true);
//        SkeletonDebugger skeletonDebug = new SkeletonDebugger("skeleton", sk);
//        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        mat.getAdditionalRenderState().setWireframe(true);
//        mat.setColor("Color", ColorRGBA.Green);
//        mat.setFloat("PointSize", 7f);
//        mat.getAdditionalRenderState().setDepthTest(false);
//        skeletonDebug.setMaterial(mat);
//
//        ((Node) s).attachChild(skeletonDebug);
    }

    private void playFirstAnim(Spatial s) {

        AnimControl control = s.getControl(AnimControl.class);
        if (control != null) {
            if (control.getAnimationNames().size() > 0) {
                control.createChannel().setAnim(control.getAnimationNames().iterator().next());
            }
        }
        if (s instanceof Node) {
            Node n = (Node) s;
            for (Spatial spatial : n.getChildren()) {
                playFirstAnim(spatial);
            }
        }
    }

    @Override
    public void simpleUpdate(float tpf) {
        if (!useAutoRotate) {
            return;
        }
        time += tpf;
        autoRotate.rotate(0, tpf * 0.5f, 0);
        if (time > duration) {
            assets.get(assetIndex).removeFromParent();
            assetIndex = (assetIndex + 1) % assets.size();
            if (assetIndex == 0) {
                duration = 10;
            }
            probeNode.attachChild(assets.get(assetIndex));
            time = 0;
        }
    }

    private void dumpScene(Spatial s, int indent) {
        System.err.println(indentString.substring(0, indent) + s.getName() + " (" + s.getClass().getSimpleName() + ") / " +
                s.getLocalTransform().getTranslation().toString() + ", " +
                s.getLocalTransform().getRotation().toString() + ", " +
                s.getLocalTransform().getScale().toString());
        if (s instanceof Node) {
            Node n = (Node) s;
            for (Spatial spatial : n.getChildren()) {
                dumpScene(spatial, indent + 1);
            }
        }
    }
}
