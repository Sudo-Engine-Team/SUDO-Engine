package site.root3287.sudo.physics.component;

import java.util.UUID;

import site.root3287.sudo.component.Component;
import site.root3287.sudo.component.TransformationComponent;
import site.root3287.sudo.entities.Entity;
import site.root3287.sudo.physics.PhysicsObject;

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
