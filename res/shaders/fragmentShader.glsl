#version 400 core

const int maxLight = 4;

in vec2 pass_textureCoords;
in vec3 surfaceNormal;
in vec3 toLightVector[maxLight];
in vec3 toCameraVector;

out vec4 our_Color;

uniform sampler2D textureSampler;
uniform vec3 lightColour[maxLight];
uniform float shineDamper;
uniform float reflectivity;

void main() {

	vec3 unitNormal = normalize(surfaceNormal);
	vec3 unitVectorToCamera = normalize(toCameraVector);
	vec3 totalDiffuse = vec3(0.0);
	vec3 totalSpecular = vec3(0.0);
	
	for(int i=0;i<maxLight;i++){
		vec3 unitLightVector = normalize(toLightVector[i]);
		float nDotl = dot(unitNormal, unitLightVector);
		float brightness = max(nDotl, 0.0);
		vec3 lightDirection = -unitLightVector;
		vec3 reflectedLightDirection = reflect(lightDirection, unitNormal);
		float specularFactor = dot(reflectedLightDirection, unitVectorToCamera);
		specularFactor = max(specularFactor, 0.0);
		float dampedFactor = pow(specularFactor, shineDamper);
		totalDiffuse = totalDiffuse + brightness * lightColour[i];
		totalSpecular = totalSpecular + dampedFactor * reflectivity * lightColour[i];
	}
	totalDiffuse = max(totalDiffuse, 0.2);
	our_Color = vec4(totalDiffuse, 1.0) * texture(textureSampler, pass_textureCoords)+ vec4(totalSpecular, 1.0);

}
