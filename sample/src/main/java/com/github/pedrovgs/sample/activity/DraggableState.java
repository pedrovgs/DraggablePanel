/*
 * Copyright (C) 2014 Pedro Vicente Gómez Sánchez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.pedrovgs.sample.activity;

import java.io.Serializable;

/**
 * Enum created to represent the DraggablePanel and DraggableView different states.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public enum DraggableState implements Serializable {

  MINIMIZED, MAXIMIZED, CLOSED_AT_LEFT, CLOSED_AT_RIGHT;

}
