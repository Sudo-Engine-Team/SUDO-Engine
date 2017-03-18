package site.root3287.lwjgl.physics.component;

import java.util.UUID;

import site.root3287.lwjgl.component.Component;
import site.root3287.lwjgl.component.TransformationComponent;
import site.root3287.lwjgl.entities.Entity;
import site.root3287.lwjgl.physics.PhysicsObject;

public class PhysicsComponent extends Component {
	private UUID id;
	public PhysicsObject physics;
	public PhysicsComponent(UUID id) {
		this.id = id;
		if(!Entity.hasComponent(id, TransformationComponent.class)){
			throw new IllegalAccessError("You cannot have a physics component without an transformation component!");
		}
	}
	
	public void update(float delta){
		//update the entity position
		physics.position = Entity.getComponent(this.id, TransformationComponent.class).position;
		physics.velocity = Entity.getComponent(this.id, TransformationComponent.class).velocity;
		
		//update the velocity
		physics.intergrate(delta);
	}
}
