package site.root3287.lwjgl.physics.component;

import java.util.UUID;

import site.root3287.lwjgl.component.Component;
import site.root3287.lwjgl.component.TransformationComponent;
import site.root3287.lwjgl.entities.Entity;
import site.root3287.lwjgl.physics.PhysicsObject;

public abstract class PhysicsComponent extends Component {
	public UUID id;
	public PhysicsObject physics;
	public PhysicsComponent(UUID id) {
		this.id = id;
		if(!Entity.hasComponent(id, TransformationComponent.class)){
			throw new IllegalAccessError("You cannot have a physics component without an transformation component!");
		}
	}
	
	public abstract void update(float delta);
}
