#version 400 core

const int maxLight = 4;

in vec2 pass_textureCoordinates;
in vec3 surfaceNormal;
in vec3 toLightVector[maxLight];
in vec3 toCameraVector;
in float visibility;

out vec4 out_Color;

uniform sampler2D modelTexture;
uniform vec3 lightColour[maxLight];
uniform float shineDamper;
uniform float reflectivity;
uniform vec3 skyColour;

void main(void){
	vec3 totalDiffuse = vec3(0.0);
	vec3 totalSpecular = vec3(0.0);

	vec3 unitNormal = normalize(surfaceNormal);
	vec3 unitVectorToCamera = normalize(toCameraVector);

	for(int i = 0; i<maxLight; i++){

		vec3 unitLightVector = normalize(toLightVector[i]);
		float nDotl = dot(unitNormal,unitLightVector);
		float brightness = max(nDotl,0.2);
		vec3 lightDirection = -unitLightVector;
		vec3 reflectedLightDirection = reflect(lightDirection,unitNormal);
		float specularFactor = dot(reflectedLightDirection , unitVectorToCamera);
		specularFactor = max(specularFactor,0.0);
		float dampedFactor = pow(specularFactor,shineDamper);
		totalSpecular = totalSpecular + dampedFactor * reflectivity * lightColour[i];
		totalDiffuse = totalDiffuse + brightness * lightColour[i];
	}

	totalDiffuse = max(totalDiffuse, 0.2);

	out_Color =  vec4(totalDiffuse,1.0) * texture(modelTexture,pass_textureCoordinates) + vec4(totalSpecular,1.0);
	out_Color = mix(vec4(skyColour, 1.0), out_Color, visibility);
}
