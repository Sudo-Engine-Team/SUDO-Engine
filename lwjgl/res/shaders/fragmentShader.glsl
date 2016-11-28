#version 400 core

in vec2 passTextureCoords;

out vec4 outColour;

uniform sampler2D textureSampler;

void main(void){
	outColour = texture(textureSampler, passTextureCoords);
}