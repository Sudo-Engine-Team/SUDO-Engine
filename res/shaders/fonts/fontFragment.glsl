#version 330

in vec2 pass_textureCoords;

out vec4 out_colour;

uniform vec3 colour;
uniform sampler2D fontAtlas;

uniform float isDistanceField;
uniform float width;
uniform float edge;
uniform float borderWidth;
uniform float borderEdge;
uniform vec3 outlineColour;

void main(void){
	if(isDistanceField == 0.0){
		out_colour = vec4(colour, texture(fontAtlas, pass_textureCoords).a);
	}else{
		float distance = 1.0 - texture(fontAtlas, pass_textureCoords).a;
		float alpha = 1.0-smoothstep(width, width+edge, distance);
		float outlineAlpha = 1.0-smoothstep(borderWidth, borderWidth+borderEdge, distance);

		float overallAlpha = alpha + (1.0 - alpha)*outlineAlpha;
		vec3 overallColour = mix(outlineColour, colour, alpha/overallAlpha);

		out_colour = vec4(overallColour, overallAlpha);
	}

}
