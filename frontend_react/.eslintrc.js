module.exports = {
  env: {
    browser: true,
    es2021: true
  },
  extends: [
    "plugin:react/recommended",
    "eslint:recommended",
    "airbnb",
    "eslint-config-prettier"
  ],
  parserOptions: {
    ecmaFeatures: {
      jsx: true
    },
    ecmaVersion: "latest",
    sourceType: "module"
  },
  plugins: ["react"],
  rules: {
    "no-param-reassign": [
      "error",
      { props: true, ignorePropertyModificationsFor: ["state"] }
    ]
    ,"import/no-unresolved" : 0
    ,"arrow-body-style": ["error", "always"]
    ,"object-shorthand": ["error", "always"]
  },
  settings: {
    "import/resolver": {
      alias: {
        map: [
          // ["@components", "./src/components"],
          // ["@screens", "./src/screens"],
          ["@styles", "./src/assets/styles"],
          ["@images", "./src/assets/images"],
          // ["@utils", "./src/utils"]
        ],
        extensions: [".ts", ".js", ".jsx", ".json", ".svg"]
      }
    }
  }
};
