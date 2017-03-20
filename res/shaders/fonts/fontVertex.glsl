#version 330

in vec2 position;
in vec2 textureCoords;

out vec2 pass_textureCoords;

uniform mat4 translation;

void main(void){

	gl_Position = translation*vec4(position, 0.0, 1.0);
	pass_textureCoords = textureCoords;

}
