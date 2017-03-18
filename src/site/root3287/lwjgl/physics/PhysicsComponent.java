package site.root3287.lwjgl.physics;

import java.util.UUID;

import site.root3287.lwjgl.component.Component;
import site.root3287.lwjgl.component.TransformationComponent;
import site.root3287.lwjgl.entities.Entity;

public class PhysicsComponent extends Component {
	private UUID id;
	public PhysicsComponent(UUID id) {
		this.id = id;
		if(!Entity.hasComponent(id, TransformationComponent.class)){
			throw new IllegalAccessError("You cannot have a physics component without an transformation component!");
		}
	}
	
	public void Intergrate(float delta){
		
	}
}
